
## 1-Sorting a TreeSet of Custom Objects in Java by Multiple Criteria

```java
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

class Person {
    private String fullName;
    private LocalDateTime startDatetime;

    public Person(String fullName, LocalDateTime startDatetime) {
        this.fullName = fullName;
        this.startDatetime = startDatetime;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    // Define comparator inside the Person class
    public static Comparator<Person> getComparator() {
        return Comparator.comparing(Person::getStartDatetime)
                         .thenComparing(Person::getFullName);
    }

    @Override
    public String toString() {
        return fullName + " - " + startDatetime;
    }
}

public class TreeSetExample {
    public static void main(String[] args) {
        // Use the comparator from Person class
        Set<Person> people = new TreeSet<>(Person.getComparator());

        // Adding people
        people.add(new Person("John Doe", LocalDateTime.of(2025, 3, 9, 10, 0)));
        people.add(new Person("Alice Smith", LocalDateTime.of(2025, 3, 9, 9, 30)));
        people.add(new Person("Bob Brown", LocalDateTime.of(2025, 3, 9, 10, 0)));
        people.add(new Person("John Doe", LocalDateTime.of(2025, 3, 9, 8, 45)));

        // Print sorted TreeSet
        for (Person p : people) {
            System.out.println(p);
        }
    }
}

```

## 2- Handling Multiple Name Fields and Start DateTime with a Custom Comparator

```java
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

class Person {
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDateTime startDatetime;

    public Person(String firstName, String middleName, String lastName, LocalDateTime startDatetime) {
        this.firstName = firstName;
        this.middleName = middleName != null ? middleName : ""; // Handle null middle names
        this.lastName = lastName;
        this.startDatetime = startDatetime;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    // Define comparator inside the Person class
    public static Comparator<Person> getComparator() {
        return Comparator.comparing(Person::getStartDatetime)
                         .thenComparing(Person::getLastName)
                         .thenComparing(Person::getFirstName)
                         .thenComparing(Person::getMiddleName);
    }

    @Override
    public String toString() {
        return firstName + " " + (middleName.isEmpty() ? "" : middleName + " ") + lastName + " - " + startDatetime;
    }
}

public class TreeSetExample {
    public static void main(String[] args) {
        // Use the comparator from Person class
        Set<Person> people = new TreeSet<>(Person.getComparator());

        // Adding people
        people.add(new Person("John", "A.", "Doe", LocalDateTime.of(2025, 3, 9, 10, 0)));
        people.add(new Person("Alice", "", "Smith", LocalDateTime.of(2025, 3, 9, 9, 30)));
        people.add(new Person("Bob", "B.", "Brown", LocalDateTime.of(2025, 3, 9, 10, 0)));
        people.add(new Person("John", "C.", "Doe", LocalDateTime.of(2025, 3, 9, 10, 0)));
        people.add(new Person("John", null, "Doe", LocalDateTime.of(2025, 3, 9, 10, 0))); // Null middle name
        people.add(new Person("John", "A.", "Doe", LocalDateTime.of(2025, 3, 9, 8, 45)));

        // Print sorted TreeSet
        for (Person p : people) {
            System.out.println(p);
        }
    }
}

```