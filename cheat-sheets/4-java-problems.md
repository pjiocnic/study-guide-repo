<h1>Solutions to Java Problems</h1>

<!-- TOC -->

- [1. How to extract contiguous digits from a sentence?](#1-how-to-extract-contiguous-digits-from-a-sentence)
- [2. Java Examples for Coding Best Practices](#2-java-examples-for-coding-best-practices)
- [3. JSON to Java](#3-json-to-java)
- [4. Java examples by AWS](#4-java-examples-by-aws)
- [5. Comparing examples](#5-comparing-examples)
- [6. Add if absent](#6-add-if-absent)
- [7. Maintaining a sort order](#7-maintaining-a-sort-order)
- [8. Sorting by different ways](#8-sorting-by-different-ways)
- [9. Implementing different equalsAndHashCode methods](#9-implementing-different-equalsandhashcode-methods)
- [10. Sorting and Unique TreeSets](#10-sorting-and-unique-treesets)
- [11. ENUM example](#11-enum-example)
- [Conditional setting](#conditional-setting)

<!-- /TOC -->

# 1. How to extract contiguous digits from a sentence?

```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractDigitsFromSentence {
    public static void main(String[] args) {
        // Input sentence
        String sentence = "The price is $25.99 and the discount is 10%.";

        // Define the regular expression pattern
        Pattern pattern = Pattern.compile("\\d+"); // This matches one or more digits

        // Create a Matcher object
        Matcher matcher = pattern.matcher(sentence);

        // Iterate through the matches and extract the digits
        while (matcher.find()) {
            String digits = matcher.group(); // Get the matched digits as a string
            System.out.println("Found digits: " + digits);
        }
    }
}
```

# 2. Java Examples for Coding Best Practices

1. [Powertools for AWS Lambda (Java)](https://github.com/aws-powertools/powertools-lambda-java/tree/main)
1. [Simplifying serverless best practices with AWS Lambda Powertools Java by Pankaj Agrawal](https://aws.amazon.com/blogs/opensource/simplifying-serverless-best-practices-with-aws-lambda-powertools-java/)
1. [Java on AWS Immersion Day](https://github.com/aws-samples/java-on-aws/tree/main)
- Has some ObjectMapper usage example
1. [Project uses new Lambda/Generic features of Java](https://github.com/aws-samples/aws-lambda-servlet/blob/main/src/main/java/com/aws/samples/lambda/servlet/LambdaWebServletProcessor.java)
- Try.run
- mapTry
- List.ofAll(newServlets).appendAll(existingServlets).distinct();
- Method references

# 3. JSON to Java

1. [Generate a Java Class From JSON](https://www.baeldung.com/java-generate-class-from-json)
1. [Converting JSON to POJOs Using Java](https://dzone.com/articles/converting-json-to-pojos-using-java)

# 4. Java examples by AWS

1. [Mutual TLS for Java based AWS Lambda functions](https://github.com/aws-samples/serverless-mutual-tls/tree/main/software)
1. [AWS SDK for Java](https://github.com/aws/aws-sdk-java/tree/master)
1. [AWS SDK for Java Documentation](https://docs.aws.amazon.com/sdk-for-java/index.html)
1. [[MY NEXT] AWS SDK for Java (v2) code examples](https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2)
1. [Managing Dependencies with AWS SDK for Java – Bill of Materials module (BOM) by Manikandan Subramanian ](https://aws.amazon.com/blogs/developer/managing-dependencies-with-aws-sdk-for-java-bill-of-materials-module-bom/)

# 5. Comparing examples

```java

public interface InterfaceA {
    String getName();
    int getAge();

    default boolean commonEquals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        InterfaceA other = (InterfaceA) obj;
        return this.getName().equals(other.getName()) && this.getAge() == other.getAge();
    }
}

public class ClassA implements InterfaceA {
    private String name;
    private int age;

    // Constructor and other methods

    // No need to override equals, it will use the default method in InterfaceA
}

public class ClassB implements InterfaceA {
    private String name;
    private int age;

    // Constructor and other methods

    // No need to override equals, it will use the default method in InterfaceA
}

```

# 6. Add if absent

1. Example 1

```java
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();

        // Your new person to be added
        Person newPerson = new Person("John", 25);

        // Check if certain conditions are met before adding the new person
        boolean shouldAdd = shouldAddPerson(personList, newPerson);

        if (shouldAdd) {
            personList.add(newPerson);
            System.out.println("New person added!");
        } else {
            System.out.println("Person not added. Matching values found.");
        }
    }

    // Method to check if certain conditions are met before adding the new person using streams
    private static boolean shouldAddPerson(List<Person> list, Person newPerson) {
        // Use streams and lambdas to check if matching values are found
        return list.stream()
                .noneMatch(person -> person.getName().equals(newPerson.getName()) && person.getAge() == newPerson.getAge());
    }
}

class Person {
    // Your Person class with fields and constructor
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter methods for fields
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

```

2. Example 2

```java
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Person2> personSet = new HashSet<>();

        // Your new person to be added
        Person2 newPerson = new Person2("John", 25);

        // Add the new person to the set
        boolean added = personSet.add(newPerson);

        if (added) {
            System.out.println("New person added!");
        } else {
            System.out.println("Person not added. Matching values found.");
        }
    }
}

class Person1 {
    // Your Person class with fields and constructor
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter methods for fields
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Override equals and hashCode for proper comparison in Set
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return 31 * name.hashCode() + age;
    }
}

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person2 {
    @EqualsAndHashCode.Include
    private String name;

    private int age;

    public Person2(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

# 7. Maintaining a sort order

```java
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.TreeSet;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person implements Comparable<Person> {
    @EqualsAndHashCode.Include
    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person otherPerson) {
        // Compare based on the name first
        int nameComparison = this.name.compareTo(otherPerson.name);

        if (nameComparison != 0) {
            return nameComparison;
        }

        // If names are the same, compare based on age
        return Integer.compare(this.age, otherPerson.age);
    }

    public static void main(String[] args) {
        // Use a TreeSet to maintain sorted order
        Set<Person> personSet = new TreeSet<>();

        // Add persons to the set
        personSet.add(new Person("Alice", 30));
        personSet.add(new Person("Bob", 25));
        personSet.add(new Person("Alice", 25));  // This will not be added since it's considered equal to the first person

        // Print the sorted set
        personSet.forEach(System.out::println);
    }
}

```

# 8. Sorting by different ways

```java
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.TreeSet;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
    @EqualsAndHashCode.Include
    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        // Sorting by name
        Set<Person> nameSortedSet = new TreeSet<>(new NameComparator());
        nameSortedSet.add(new Person("Alice", 30));
        nameSortedSet.add(new Person("Bob", 25));
        nameSortedSet.add(new Person("Alice", 25));

        System.out.println("Sorted by name:");
        nameSortedSet.forEach(System.out::println);

        // Sorting by age
        Set<Person> ageSortedSet = new TreeSet<>(new AgeComparator());
        ageSortedSet.add(new Person("Alice", 30));
        ageSortedSet.add(new Person("Bob", 25));
        ageSortedSet.add(new Person("Alice", 25));

        System.out.println("\nSorted by age:");
        ageSortedSet.forEach(System.out::println);
    }
}

class NameComparator implements java.util.Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
}

class AgeComparator implements java.util.Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.getAge(), p2.getAge());
    }
}

```

# 9. Implementing different equalsAndHashCode methods

```java
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Person {
    @EqualsAndHashCode.Include
    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        // Using default equals and hashCode
        Person person1 = new Person("Alice", 25);
        Person person2 = new Person("Alice", 25);

        System.out.println("Default equals and hashCode:");
        System.out.println(person1.equals(person2));  // true
        System.out.println(person1.hashCode() == person2.hashCode());  // true

        // Using only name for equals and hashCode
        @EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false, exclude = {"age"})
        class PersonByName extends Person {
            public PersonByName(String name, int age) {
                super(name, age);
            }
        }

        PersonByName personByName1 = new PersonByName("Alice", 25);
        PersonByName personByName2 = new PersonByName("Alice", 30);

        System.out.println("\nEquals and hashCode only based on name:");
        System.out.println(personByName1.equals(personByName2));  // true
        System.out.println(personByName1.hashCode() == personByName2.hashCode());  // true

        // Using only age for equals and hashCode
        @EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false, exclude = {"name"})
        class PersonByAge extends Person {
            public PersonByAge(String name, int age) {
                super(name, age);
            }
        }

        PersonByAge personByAge1 = new PersonByAge("Alice", 25);
        PersonByAge personByAge2 = new PersonByAge("Bob", 25);

        System.out.println("\nEquals and hashCode only based on age:");
        System.out.println(personByAge1.equals(personByAge2));  // true
        System.out.println(personByAge1.hashCode() == personByAge2.hashCode());  // true
    }
}

```

# 10. Sorting and Unique TreeSets

```java
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, of = {"B", "C"})
public class MyClass {
    private LocalDateTime A;
    @EqualsAndHashCode.Include
    private String B;
    @EqualsAndHashCode.Include
    private MyEnum C;

    public MyClass(LocalDateTime A, String B, MyEnum C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public static void main(String[] args) {
        Set<MyClass> myClassSet = new TreeSet<>(new MyComparator());

        // Adding elements to the set
        myClassSet.add(new MyClass(LocalDateTime.now(), "B1", MyEnum.VALUE1));
        myClassSet.add(new MyClass(LocalDateTime.now().minusDays(1), "B2", MyEnum.VALUE2));
        myClassSet.add(new MyClass(LocalDateTime.now().minusDays(1), "B2", MyEnum.VALUE2)); // Duplicate B and C, but different A
        myClassSet.add(new MyClass(LocalDateTime.now(), "B1", MyEnum.VALUE1)); // Duplicate A, B, and C

        // Printing the set
        myClassSet.forEach(System.out::println);
    }
}

// Enum definition
enum MyEnum {
    VALUE1, VALUE2, VALUE3
}

// Custom comparator
class MyComparator implements Comparator<MyClass> {
    @Override
    public int compare(MyClass o1, MyClass o2) {
        int compareA = o1.getA().compareTo(o2.getA());
        if (compareA != 0) {
            return compareA;
        }

        int compareB = o1.getB().compareTo(o2.getB());
        if (compareB != 0) {
            return compareB;
        }

        return o1.getC().compareTo(o2.getC());
    }
}

```

# 11. ENUM example

```java
import java.util.Arrays;

public class EnumExample {

    // Define an enum with multiple types and a constructor
    public enum AnimalType {
        DOG("Dog"),
        CAT("Cat"),
        BIRD("Bird"),
        UNKNOWN("Unknown");

        private final String typeName;

        AnimalType(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    // Method to convert a string parameter to the corresponding enum value using streams
    public static AnimalType getAnimalType(String type) {
        return Arrays.stream(AnimalType.values())
                     .filter(animalType -> animalType.getTypeName().equalsIgnoreCase(type))
                     .findFirst()
                     .orElse(AnimalType.UNKNOWN);
    }

    public static void main(String[] args) {
        // Example usage
        String userInput = "Fish"; // This will result in UNKNOWN
        AnimalType animalType = getAnimalType(userInput);
        System.out.println("Selected animal type: " + animalType);
    }
}

```

# Conditional setting

```java
import java.util.Arrays;

public class MyClass {
    private String animalType;

    // Constructor, getters, setters

    // Method to set the animal type based on string value using lambda
    public void setAnimalTypeFromString(String animalType) {
        AnimalTypeEnum enumValue = Arrays.stream(AnimalTypeEnum.values())
                                        .filter(value -> value.toString().equalsIgnoreCase(animalType))
                                        .findFirst()
                                        .orElse(AnimalTypeEnum.UNKNOWN);

        if (enumValue == AnimalTypeEnum.DOG || enumValue == AnimalTypeEnum.CAT) {
            this.animalType = enumValue.toString();
        } else {
            this.animalType = AnimalTypeEnum.UNKNOWN.toString();
        }
    }
}

// Define the AnimalTypeEnum with three values: DOG, CAT, and UNKNOWN
enum AnimalTypeEnum {
    DOG,
    CAT,
    UNKNOWN;
}
```