```sql
SELECT
    table_name,
    count_x_not_null,
    total_rows,
    ROUND(100.0 * count_x_not_null / NULLIF(total_rows, 0), 2) AS pct_with_x
FROM (
    SELECT 'TABLE_1' AS table_name,
           COUNT(CASE WHEN X IS NOT NULL THEN 1 END) AS count_x_not_null,
           COUNT(*) AS total_rows
    FROM TABLE_1

    UNION ALL

    SELECT 'TABLE_2',
           COUNT(CASE WHEN X IS NOT NULL THEN 1 END),
           COUNT(*)
    FROM TABLE_2

    UNION ALL

    SELECT 'TABLE_3',
           COUNT(CASE WHEN X IS NOT NULL THEN 1 END),
           COUNT(*)
    FROM TABLE_3

    -- Continue up to TABLE_15

    UNION ALL

    -- GRAND TOTAL ROW
    SELECT 'TOTAL',
           SUM(count_x_not_null),
           SUM(total_rows)
    FROM (
        SELECT COUNT(CASE WHEN X IS NOT NULL THEN 1 END) AS count_x_not_null,
               COUNT(*) AS total_rows FROM TABLE_1
        UNION ALL
        SELECT COUNT(CASE WHEN X IS NOT NULL THEN 1 END), COUNT(*) FROM TABLE_2
        UNION ALL
        SELECT COUNT(CASE WHEN X IS NOT NULL THEN 1 END), COUNT(*) FROM TABLE_3
        -- Continue up to TABLE_15
    )
)
ORDER BY CASE WHEN table_name = 'TOTAL' THEN 1 ELSE 0 END, table_name;
```