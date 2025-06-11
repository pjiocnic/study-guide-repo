
# How to add a table in the comments

```java
/**
 * Description
 * <pre>
 | Key   |    Value    |
 |-------|-------------|
 | 1     | Value1      |
 | 2     | Value2      |
 *</pre>
 */

 /**
 *  2 Device Connected possible states matrix
 *
|         | DIALING | ON_HOLD | RINGING | ON_CALL | IDLE |
|---------|---------|---------|---------|---------|------|
| DIALING |   --    | YES     | YES     | YES     | YES  |
| ON_HOLD |  YES    | YES     | YES     | YES     | YES  |
| RINGING |  YES    | YES     | YES     | YES     | YES  |
| ON_CALL |  YES    | YES     | YES     | --      | YES  |
| IDLE    |  YES    | YES     | YES     | YES     | YES  |
 */
```

# References

1. https://www.tablesgenerator.com/markdown_tables#
2. https://www.baeldung.com/javadoc