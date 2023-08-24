


1. To check if all child rows in a specific status in Oracle SQL, you can use a SQL query with a WHERE clause that filters for the desired status and a subquery to count the total number of child rows and the number of child rows in the specific status. Then, you can compare these two counts to determine if all child rows have the specific status.

Here's an example SQL query to achieve this:

```sql
SELECT
    parent_id,
    CASE
        WHEN COUNT(*) = SUM(CASE WHEN status = 'desired_status' THEN 1 ELSE 0 END)
        THEN 'All child rows have the desired status'
        ELSE 'Not all child rows have the desired status'
    END AS status_check_result
FROM
    your_table_name
WHERE
    parent_id = your_parent_id
GROUP BY
    parent_id;
```

```sql
SELECT DISTINCT parent_id
FROM your_table_name
WHERE parent_id IN (
    SELECT DISTINCT parent_id
    FROM your_table_name
    WHERE status = 'desired_status'
);
```

# Describe table

```sql
SELECT column_name, data_type, data_length, data_precision, data_scale, nullable, data_default, column_id, comments
FROM user_tab_columns
WHERE table_name = 'YOUR_TABLE_NAME';

SELECT table_name FROM user_tables WHERE table_name = 'YOUR_TABLE_NAME';
```