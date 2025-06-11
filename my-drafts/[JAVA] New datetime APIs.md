src: https://learning.oreilly.com/library/view/learn-java-with/9781837637188/B19793_12.xhtml#_idParaDest-311

java.time package (from JDK 1.8) uses ISO calendar system and represents dates, times, timezones, instants, periods, and durations

It replaces **java.util.Date**, **java.util.Calendar**, and **java.text.DateFormat**

# UTC

**UTC** is a successor to Greenwich Mean Time (**GMT**) and mean the same thing

The time zone uses `UTC+/-00:00`, which is sometimes denoted by the letter Z – a reference to the equivalent nautical time zone (**GMT**). Since the NATO phonetic alphabet word for Z is **“Zulu”**, **UTC** is sometimes referred to as “**Zulu time**”

**Examples**

`2020–01–27T06:00:00Z (Greenwich)`

`2020–01–27T00:00:00–06:00 (México City)`

`2020–01–27T14:00:00+08:00 (Beijing)`

Mongo's representation: `2012-11-20T05:05:15.243Z`

# Terminologies

1. **Instant**: An instant is a numeric timestamp. It is useful for logging and persistence. Historically, System.currentTimeMillis() would have been used. System.currentTimeMillis() returns the number of milliseconds since the “epoch day” (`Jan 1st, 1970 at 00:00:00 UTC`). The epoch is a fixed time from which all timestamps are calculated.
2. **LocalDate**: Stores a date without a time. This is useful for representing birthdays such as `2000-10-21`. As it follows ISO-8601, the format is year-month-day.
3. **LocalTime**: Stores a time without a date. This is useful for representing opening/closing hours such as `09:00`.
4. **LocalDateTime**: Stores a date and time such as `2000-10-21T17:00`. Note the “T” used as a date and time separator. This is useful for representing the date and time of a scheduled event, such as a concert.
5. **ZonedDateTime**: Represents a “full” date-time with a time zone and resolved offset from UTC. For example, `2023-02-14T16:45+01:00[Europe/Zurich]` is the date-time for the Europe/Zurich time zone and is 1 hour ahead of UTC.
6. **Duration**: An amount of time, represented in seconds (and nanoseconds); for example, “54 seconds.”
7. **Period**: Represents an amount of time in units more meaningful to humans, such as years or days. For example, “3 years, 6 months, and 12 days.”

# Common Patterns

1. A static factory method for creating instances – for example,

```javascript {.line-numbers}
LocalDate ld1 = LocalDate.of(2023, 3, 17);
```

2. A static factory method for creating instances – for example,

```javascript {.line-numbers}
LocalDate ld2 = LocalDate.parse("2023-03-17");
```

3. Gets the value of something – for example,

```javascript {.line-numbers}
int dayOfMonth = ld2.getDayOfMonth(); // 17
```

3. Checks if something is true – for example,

```javascript {.line-numbers}
boolean isLeapYear = ld2.isLeapYear(); // false
```

4. The immutable equivalent of a setter method – for example,

```javascript {.line-numbers}
LocalDate ld3 = ld2.withDayOfMonth(25); // 2023-03-25
```

5. Adds an amount to an object – for example,
```javascript {.line-numbers}
LocalDate ld4 = ld3.plusDays(2); // 2023-03-27
```

6. Subtracts an amount from an object – for example,

```javascript {.line-numbers}
LocalDate ld5 = ld4.minusMonths(2); // 2023-01-27
```

7. Combines this object with another – for example,

```javascript {.line-numbers}
LocalDateTime ldt1 = ld5.atTime(13, 45, 10); // 2023-01-27T13:45:10
```

8. Obtain an instance of LocalDateTime from year, month, day, hour, minute, second and nanosecond.

```javascript {.line-numbers}
// LocalDateTime.of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond)
LocalDateTime.of(2023, 12, 30, 12, 30, 40,0)

// In Mongo this will be stored as UTC 2023-12-30T12:30:45.000Z.  Note the Z at the end
```

# Converting between TimeZones


# References

1. [For ISO 8601 format](https://medium.com/@biseldev/understanding-iso-8601-and-utc-ce8d99609055)
2. [Introduction to the Java 8 Date/Time API](https://www.baeldung.com/java-8-date-time-intro)