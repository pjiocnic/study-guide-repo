Great question. If `updated != 1`, it means Oracle didn’t match exactly one row for `WHERE A = ?`. In a single-threaded run, the usual culprits are data/type mismatches or querying a different schema/table than you think. Here’s a focused troubleshooting checklist plus a few defensive fixes.

# Quick checks (most common causes)

1. **No matching row (value mismatch)**

* Trailing/leading spaces in your `a` value vs. the DB row (especially if `A` is `CHAR`).
* Case sensitivity differences (your API/transform changed case).
* Hidden characters (non-breaking space, control chars).

2. **Type mismatch**

* Column `A` is `NUMBER`, but you bind it as `String`. Oracle will try to convert, but `"00123"` vs `123` or non-numeric strings will fail to match.
* Fix by using the **correct setter**: `setLong`, `setBigDecimal`, etc.

3. **Wrong schema/table**

* `SELECT` might run against `SCHEMA1.A`, while `UPDATE` hits `SCHEMA2.A` due to synonyms/default schema.
* Always qualify: `SCHEMA.A`.

4. **Duplicates**

* If `updated > 1`: there are duplicate rows (no PK). Add a **primary key/unique index** on `A`.

5. **You selected a different slice than you update**

* If your select uses `FOR UPDATE SKIP LOCKED` in one transaction and you update in another (e.g., `REQUIRES_NEW`), you might be updating outside that lock scope. (It would usually block, not return 0—but worth aligning select+update in the same transaction per row.)

# Add targeted logging/verification

Drop in these probes before your `UPDATE` to see exactly what Oracle would match:

```java
// 1) Log lengths and hex to catch whitespace/control chars
private static String hex(String s) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) sb.append(String.format("%02X ", (int)c));
    return sb.toString().trim();
}

private void debugValue(String a) {
    log.info("Updating A = '{}' (len={}, hex={})", a, a == null ? -1 : a.length(), a == null ? "null" : hex(a));
}

// 2) Pre-count matching rows (schema-qualified)
private int precheckCount(String a) {
    return jdbc.queryForObject(
        "SELECT COUNT(*) FROM SCHEMA.A WHERE A = ?",
        new Object[]{a},
        Integer.class
    );
}
```

Use them in your loop:

```java
debugValue(a);
int cnt = precheckCount(a);
if (cnt == 0) {
    log.warn("No row matches WHERE A = ?  (value='{}')", a);
}
int updated = jdbc.update("UPDATE SCHEMA.A SET B = ? WHERE A = ?", b, a);
if (updated != 1) {
    log.error("Unexpected update count={} for A='{}' (precheck count was {})", updated, a, cnt);
}
```

# Defensive fixes you can adopt

## 1) Make the WHERE more specific

If only rows with `B IS NULL` should be touched, add that:

```java
int updated = jdbc.update(
    "UPDATE SCHEMA.A SET B = ? WHERE A = ? AND B IS NULL",
    ps -> { ps.setString(1, b); ps.setString(2, a); }
);
```

If this returns 0, you now **know** it was already populated between select and update (or by a prior run).

## 2) Normalize your parameter before binding

Trim and collapse whitespace if your business logic allows:

```java
String aNorm = a == null ? null : a.trim();
```

If column `A` is `CHAR`, consider trimming the DB side for certainty (only if it won’t break indexes/plan):

```sql
UPDATE SCHEMA.A SET B = :b
WHERE TRIM(A) = :aTrim AND B IS NULL
```

*(Prefer normalizing inputs and keeping indexes sargable rather than wrapping the column in functions.)*

## 3) Bind with the correct type

If `A` is numeric:

```java
long aNum = Long.parseLong(a); // or however you hold it
int updated = jdbc.update("UPDATE SCHEMA.A SET B = ? WHERE A = ?", ps -> {
    ps.setString(1, b);
    ps.setLong(2, aNum);
});
```

## 4) Fully qualify the table

Be explicit to avoid schema surprises:

```java
"UPDATE YOUR_SCHEMA.A SET B = ? WHERE A = ?"
```

Do the same in your `SELECT`.

## 5) Enforce uniqueness

If `updated > 1` ever appears, add a primary key:

```sql
ALTER TABLE SCHEMA.A ADD CONSTRAINT A_PK PRIMARY KEY (A);
```

(If there are existing dupes, clean them up first.)

# If you’re using `FOR UPDATE SKIP LOCKED`

* Keep **select and update in the same transaction** when single-threaded, or
* If doing per-row `REQUIRES_NEW`, do both the **select (for that row)** and **update** inside that same `REQUIRES_NEW` method, or avoid `FOR UPDATE` entirely in single-threaded runs.

Example per-row transaction:

```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
public ResultRow processOne(String a) {
    // optionally re-verify the row still needs work:
    Integer need = jdbc.queryForObject(
        "SELECT COUNT(*) FROM SCHEMA.A WHERE A = ? AND B IS NULL FOR UPDATE",
        new Object[]{a}, Integer.class);
    if (need == 0) return ResultRow.noId(a);

    String body = fetch(urlTemplate.replace("{id}", a));
    String extracted = extractId(body, idJsonPath);
    if (extracted == null || extracted.trim().isEmpty()) return ResultRow.noId(a);

    int updated = jdbc.update("UPDATE SCHEMA.A SET B = ? WHERE A = ?", extracted.trim(), a);
    return updated == 1 ? ResultRow.ok(a, extracted) : ResultRow.error(a, "Row not updated");
}
```

# Enable helpful logging

In `application.properties`:

```properties
logging.level.org.springframework.jdbc.core.JdbcTemplate=DEBUG
logging.level.org.springframework.jdbc.core.StatementCreatorUtils=TRACE
```

This will show SQL and parameter bindings (including types), which often immediately reveals a type or value mismatch.

---

If you paste one sample `a` that returns `updated=0`, I can suggest the exact normalization/binding needed (and we can verify with a `SELECT LENGTH(A), DUMP(A)` to spot whitespace/encoding issues).
