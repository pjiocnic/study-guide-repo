Thanks for the clarification. The failure likely stems from this part of your query:

```sql
PATH '*/*/*/*/*[local-name()="BinaryBase64Object"]'
```

When your XML contains **multiple `<Parent>` nodes**, and **each `<Parent>` has multiple `<BinaryBase64Object>` elements**, Oracle’s `XMLTable` expects a **1:1 mapping** per row unless otherwise told to "un-nest" those multiple `BinaryBase64Object` values. Otherwise, you get an error like **ORA-19025: EXTRACTVALUE returns value of only one node** or implicit Cartesian join issues.

---

### ✅ What You Need:
You must **"flatten" or "join"** each `Parent` and its **multiple** `BinaryBase64Object` children **as multiple rows** using **nested XMLTable**.

---

### ✅ Revised Query (with nested `XMLTable`):
Assuming the structure is:

```xml
<Root>
  <Parent>
    <IdentificationID>ID1</IdentificationID>
    <BinaryBase64Object>...</BinaryBase64Object>
    <BinaryBase64Object>...</BinaryBase64Object>
  </Parent>
  <Parent>
    ...
  </Parent>
</Root>
```

Here’s how you can rewrite the SQL using **nested `XMLTable`**:

```sql
SELECT
    p.ID,
    b.IMG
FROM TABLE1 F,
     XMLTable(
         '//*/*/*/*/*[local-name()="Parent"]'
         PASSING XMLTYPE(REGEXP_REPLACE(F.rslt_obj, '[^ -~]', ''))
         COLUMNS
             ID VARCHAR2(30) PATH '*[local-name()="IdentificationID"]',
             BIN_XML XMLTYPE  PATH '.'
     ) p,
     XMLTable(
         '/*/*[local-name()="BinaryBase64Object"]'
         PASSING p.BIN_XML
         COLUMNS
             IMG CLOB PATH '.'
     ) b
WHERE F.rslt_obj IS NOT NULL
  AND F.key = '1'
  AND b.IMG IS NOT NULL
  AND LENGTH(b.IMG) > 0;
```

---

### 🔍 What Changed:
- `XMLTable #1 (p)` extracts each `<Parent>` node and captures:
  - The `IdentificationID`
  - The full XML for that parent as `BIN_XML`
- `XMLTable #2 (b)` iterates over each `<BinaryBase64Object>` **inside one parent**
- You now get **one row per BinaryBase64Object**, even if a Parent has many

Would you like help adapting this to your real XML structure or testing it with a sample?