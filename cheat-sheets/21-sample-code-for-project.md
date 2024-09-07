
```java
import java.util.Objects;

public class FieldUpdater {

    public static <T> void updateFields(T source, T destination, FieldComparator<T> comparator) {
        if (!comparator.areEqual(source, destination)) {
            comparator.copyFields(source, destination);
        }
    }

    public interface FieldComparator<T> {
        boolean areEqual(T obj1, T obj2);
        void copyFields(T source, T destination);
    }

    // Example usage:
    public static class Person {
        private String name;
        private int age;

        // getters, setters, and constructor

        // Lombok's @Data annotation generates equals and hashCode methods
    }

    public static void main(String[] args) {
        Person person1 = new Person("John", 25);
        Person person2 = new Person("Jane", 30);

        FieldUpdater.updateFields(person1, person2, new PersonComparator());

        System.out.println(person2.getName());  // Output: John
        System.out.println(person2.getAge());   // Output: 25
    }

    public static class PersonComparator implements FieldComparator<Person> {
        @Override
        public boolean areEqual(Person obj1, Person obj2) {
            return Objects.equals(obj1.getName(), obj2.getName()) &&
                   Objects.equals(obj1.getAge(), obj2.getAge());
        }

        @Override
        public void copyFields(Person source, Person destination) {
            destination.setName(source.getName());
            destination.setAge(source.getAge());
        }
    }
}

```

2. If 2 different types

```java
public class FieldUpdater {

    public static <T extends CommonFields> void updateFields(T source, T destination) {
        if (source.areFieldsEqual(destination)) {
            source.copyFields(destination);
        }
    }

    public interface CommonFields {
        boolean areFieldsEqual(CommonFields other);
        void copyFields(CommonFields other);
    }

    // Example usage:
    public static class Person implements CommonFields {
        private String name;
        private int age;

        // getters, setters, and constructor

        @Override
        public boolean areFieldsEqual(CommonFields other) {
            if (other instanceof Person) {
                Person otherPerson = (Person) other;
                return Objects.equals(this.getName(), otherPerson.getName()) &&
                       Objects.equals(this.getAge(), otherPerson.getAge());
            }
            return false;
        }

        @Override
        public void copyFields(CommonFields other) {
            if (other instanceof Person) {
                Person otherPerson = (Person) other;
                this.setName(otherPerson.getName());
                this.setAge(otherPerson.getAge());
            }
        }
    }

    public static class Employee implements CommonFields {
        private String name;
        private int employeeId;

        // getters, setters, and constructor

        @Override
        public boolean areFieldsEqual(CommonFields other) {
            if (other instanceof Employee) {
                Employee otherEmployee = (Employee) other;
                return Objects.equals(this.getName(), otherEmployee.getName()) &&
                       Objects.equals(this.getEmployeeId(), otherEmployee.getEmployeeId());
            }
            return false;
        }

        @Override
        public void copyFields(CommonFields other) {
            if (other instanceof Employee) {
                Employee otherEmployee = (Employee) other;
                this.setName(otherEmployee.getName());
                this.setEmployeeId(otherEmployee.getEmployeeId());
            }
        }
    }

    public static void main(String[] args) {
        Person person1 = new Person("John", 25);
        Employee employee1 = new Employee("Jane", 123);

        Person person2 = new Person("Bob", 30);
        Employee employee2 = new Employee("Alice", 456);

        FieldUpdater.updateFields(person1, person2);
        FieldUpdater.updateFields(employee1, employee2);

        System.out.println(person2.getName());         // Output: John
        System.out.println(person2.getAge());          // Output: 25

        System.out.println(employee2.getName());        // Output: Jane
        System.out.println(employee2.getEmployeeId());  // Output: 123
    }
}

```

