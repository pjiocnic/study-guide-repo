You can achieve this using Java Streams and Lambdas with the `Collectors.joining()` method to collect the ids of the objects of a certain type into a comma-separated string. Here's an example:

Assuming the class `A` is defined as:

```java
public class A {
    private String type;
    private String id;

    // Constructor, getters, and setters
    public A(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
```

Now, to collect the `id` values of objects of a specific type (e.g., "typeA") into a comma-separated string:

```java
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<A> objects = List.of(
                new A("typeA", "1"),
                new A("typeB", "2"),
                new A("typeA", "3"),
                new A("typeA", "4"),
                new A("typeB", "5")
        );

        String result = objects.stream()
            .filter(a -> "typeA".equals(a.getType()))  // Filter by type
            .map(A::getId)                            // Map to ids
            .collect(Collectors.joining(","));         // Join ids into a comma-separated string

        System.out.println(result);  // Output: 1,3,4
    }
}
```

### Explanation:
1. `filter(a -> "typeA".equals(a.getType()))`: Filters the list to only include objects of type "typeA".
2. `map(A::getId)`: Extracts the `id` from each `A` object.
3. `collect(Collectors.joining(","))`: Collects the results into a single string, with each id separated by a comma.

This will give you the desired comma-separated string of ids for the specified type.