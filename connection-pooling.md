
551- 573


- demo: log in as SCOTT in window1, BENCHMARK in window2 and in WINDOW3 run following as SYS user

```sql
-- Query that lists currently opened physical connections and sessions
--  v$session and v$process are synonyms for views v_$session and v_$process
select s.program, s.server, p.spid server_pid, s.username
    from v$session s, v$process p
    where s.type = 'USER'
    and s.username != 'SYS'
    and p.addr(+) = s.paddr;
PROGRAM                        SERVER          SERVER_PID   USERNAME
------------------------------ --------------- ------------ ----------
sqlplus.exe                    DEDICATED       3648         SCOTT
sqlplus.exe                    DEDICATED       3028         BENCHMARK
```

Above shows two sessions, one each for user, SCOTT, and BENCHMARK.
The server_pid corresponds to each of the connections to DB
Therefore, the query shows us that there are two sessions using two separate connections

1. What happens when you reach the limit and need another connection?
That depends on the cache scheme you set

- **Scheme 1 - Dynamic:** This is the default scheme. In this scheme, the cache would automatically create new pooled connections above the what's comnfigured as maxPool

```java
occi.setCacheScheme( "dynamic" );
occi.setCacheScheme( OracleConnectionCacheImpl.DYNAMIC_SCHEME );
```

- **Scheme 2 - Fixed return null:** After the maximum limit is exceeded get a null value returned

```java
occi.setCacheScheme( "fixed_return_null_scheme" );
occi.setCacheScheme( OracleConnectionCacheImpl.FIXED_RETURN_NULL_SCHEME );
```

- Scheme 3 - Fixed wait:

# Setting Oracle Connection Cache Timeouts

1. Wait timeout:
2. Inactivity timeout:
3. Time to live timeout

# Cache Properties

Property | Default Value | Type of Connection | Impacted Description
---------|-------------- | ------------------ | -------------------

InactivityTimeout | TimeToLiveTimeout | AbandonedConnectionTimeout | ConnectionWaitTimeout |Default Value
0 (no timeout) | 0 (no timeout) | 0 (no timeout) | 0 (no timeout)
Type of Connection Impacted
Physical
Logical
Logical
Logical
Description
Sets the maximum time
in seconds a physical connection can remain idle in a connection cache. An idle connection is one that is not active and does not have a logical handle associated with it.
Sets the maximum time
in seconds that a logical connection can remain open (or checked out), after which it is returned to the cache.
Sets the maximum time
in seconds that a logical connection can remain open (or checked out) without any SQL activity on that connection, after which the logical connection is returned to the cache.

Comes into play when there is a request for a logical connection, the cache has reached the MaxLimit, and all physical connections are in use. This is the number of seconds the cache will wait for one of the physical connections currently in use to become free so that the request can be satisfied. After this timeout expires, the cache returns null.

PropertyCheckInterval 900 seconds Sets the time interval in seconds at which the cache manager inspects and enforces all specified cache properties.

ValidateConnection: If you set this property to true, the cache manager tests for validity each connection to be retrieved from the database. The default value is false.

# Additional Reading

1. [Prepared statement cache in Hiraki](https://stackoverflow.com/questions/71493599/does-hikari-cp-support-preparedstatements-cache)
2. [Connection Leak in Hiraki](https://naiyer.dev/post/2022/09/18/detecting-a-connection-leak-with-hikari/)
3. [Hiraki](https://www.baeldung.com/hikaricp)
4. [Configuring a Hikari Connection Pool with Spring Boot](https://www.baeldung.com/spring-boot-hikari)
5. [HikariCP](https://github.com/brettwooldridge/HikariCP)


# Questions to explore

1. How to reclaim inactive connections?
2. How to make the connections shortlived
3. Diifferent sizing parameters - Min, Initial and Max
4. https://docs.oracle.com/cd/E13222_01/wls/docs81/jdbc/programming.html
5. [Getting Status and Statistics for a Connection Pool](https://docs.oracle.com/cd/E13222_01/wls/docs81/jdbc/programming.html#1060759)
6. [Hiraki] How to check number of physical connections available and number in use?
7. [Hiraki] How does Hiraki use timeout (in secs) for in-use logical connections to be closed before removing it from cache?
8. [Hiraki] Is it possible to reinitialze the cache with new configuration while its running?
9. [Hiraki]

# Examples to explore
https://github.com/chargeahead/springbootconnectionleak
https://www.youtube.com/watch?v=FOQwsAQLseo