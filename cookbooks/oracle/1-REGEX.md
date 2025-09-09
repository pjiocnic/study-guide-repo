You can solve this with a **join using `LIKE`** or, more flexibly, with `REGEXP_LIKE`.

Assume:

* **TABLE\_A** has column `keyword`
* **TABLE\_B** has column `text_col`

### Option 1: Simple `LIKE` join (one keyword at a time)

```sql
SELECT a.keyword, b.text_col
FROM   table_a a
JOIN   table_b b
  ON   b.text_col LIKE '%' || a.keyword || '%';
```

* `||` concatenates `%` wildcards around the keyword.
* This lists all rows from **TABLE\_B** that contain each keyword from **TABLE\_A**.

---

### Option 2: Using `REGEXP_LIKE` (if keywords may appear in word boundaries)

```sql
SELECT a.keyword, b.text_col
FROM   table_a a
JOIN   table_b b
  ON   REGEXP_LIKE(b.text_col, '\b' || a.keyword || '\b', 'i');
```

* `\b` ensures whole word match (so "cat" won’t match "concatenate").
* `'i'` makes it case-insensitive.

---

### Option 3: Aggregate keywords found per text

If you want one row per text, listing all matched keywords:

```sql
SELECT b.id,
       LISTAGG(a.keyword, ', ') WITHIN GROUP (ORDER BY a.keyword) AS matched_keywords
FROM   table_b b
JOIN   table_a a
  ON   b.text_col LIKE '%' || a.keyword || '%'
GROUP BY b.id;
```

---

✅ Use **Option 1** if substring matches are fine.
✅ Use **Option 2** if you need case-insensitive, whole-word matching.
✅ Use **Option 3** if you want all keywords per text row aggregated together.

Would you like me to give you a **runnable demo** with small sample data (two tables with values) so you can see exactly what output each query produces?

----

Perfect 👍 Let me show you a **runnable demo** with sample data and the three approaches.

---

## 1. Create sample tables

```sql
-- Table A: keywords
CREATE TABLE table_a (
    keyword VARCHAR2(50)
);

INSERT INTO table_a (keyword) VALUES ('Oracle');
INSERT INTO table_a (keyword) VALUES ('SQL');
INSERT INTO table_a (keyword) VALUES ('database');
INSERT INTO table_a (keyword) VALUES ('cloud');

-- Table B: text data
CREATE TABLE table_b (
    id NUMBER,
    text_col VARCHAR2(200)
);

INSERT INTO table_b VALUES (1, 'Oracle SQL is powerful for databases');
INSERT INTO table_b VALUES (2, 'Python and SQL are often used together');
INSERT INTO table_b VALUES (3, 'AWS cloud is popular for hosting databases');
INSERT INTO table_b VALUES (4, 'Java is also widely used');
```

---

## 2. Option 1 – Simple substring match (`LIKE`)

```sql
SELECT a.keyword, b.id, b.text_col
FROM   table_a a
JOIN   table_b b
  ON   b.text_col LIKE '%' || a.keyword || '%';
```

**Output**

```
KEYWORD    ID   TEXT_COL
---------  ---  ------------------------------------------
Oracle      1   Oracle SQL is powerful for databases
SQL         1   Oracle SQL is powerful for databases
SQL         2   Python and SQL are often used together
database    1   Oracle SQL is powerful for databases
cloud       3   AWS cloud is popular for hosting databases
database    3   AWS cloud is popular for hosting databases
```

---

## 3. Option 2 – Whole word match (`REGEXP_LIKE`, case-insensitive)

```sql
SELECT a.keyword, b.id, b.text_col
FROM   table_a a
JOIN   table_b b
  ON   REGEXP_LIKE(b.text_col, '\b' || a.keyword || '\b', 'i');
```

* This avoids matching partial words (e.g., `cat` won’t match `concatenate`).

**Output** (same as above for this dataset, but safer for real text).

---

## 4. Option 3 – Aggregate keywords found per text

```sql
SELECT b.id,
       LISTAGG(a.keyword, ', ') WITHIN GROUP (ORDER BY a.keyword) AS matched_keywords,
       b.text_col
FROM   table_b b
JOIN   table_a a
  ON   b.text_col LIKE '%' || a.keyword || '%'
GROUP BY b.id, b.text_col;
```

**Output**

```
ID   MATCHED_KEYWORDS             TEXT_COL
---  ---------------------------  ------------------------------------------
1    Oracle, SQL, database        Oracle SQL is powerful for databases
2    SQL                          Python and SQL are often used together
3    cloud, database              AWS cloud is popular for hosting databases
```

---

👉 This way, you can see **which keywords matched each row of text**.

Would you like me to also show a **scalable version** that works if `TABLE_A` has hundreds/thousands of keywords (so you don’t hit performance issues with too many `LIKE` checks)?
