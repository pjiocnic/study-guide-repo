# 1. Predicate example

```java
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

// ...

List<String> inputList = Arrays.asList("apple", "banana", "orange", "grape");

Predicate<String> filterPredicate = new Predicate<String>() {
    @Override
    public boolean evaluate(String object) {
        return object.startsWith("a");
    }
};

List<String> filteredList = (List<String>) CollectionUtils.select(inputList, filterPredicate);
System.out.println(filteredList);

```

# 2. Filtering example

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// ...

List<String> inputList = Arrays.asList("apple", "banana", "orange", "grape");

List<String> filteredList = inputList.stream()
                                     .filter(s -> s.startsWith("a"))
                                     .collect(Collectors.toList());

System.out.println(filteredList);

```

collections.filter((SubList, Predicate))

# 3. Group by example

```java
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Map<String, String>> vehicles = Arrays.asList(
            new HashMap<>() {{
                put("VIN", "1234");
                put("STATE", "?");
                put("active", "Y");
            }},
            new HashMap<>() {{
                put("VIN", "1234");
                put("STATE", "VA");
                put("active", "Y");
            }},
            new HashMap<>() {{
                put("VIN", "56789");
                put("STATE", "?");
                put("active", "Y");
            }},
            new HashMap<>() {{
                put("VIN", "678546");
                put("STATE", "MA");
                put("active", "Y");
            }}
        );

        // Group by VIN
        Map<String, List<Map<String, String>>> groupedByVin = vehicles.stream()
            .collect(Collectors.groupingBy(vehicle -> vehicle.get("VIN")));

        // Process each group
        groupedByVin.values().forEach(group -> {
            boolean hasValidState = group.stream()
                .anyMatch(vehicle -> !"?".equals(vehicle.get("STATE")));

            if (hasValidState) {
                group.stream()
                    .filter(vehicle -> "?".equals(vehicle.get("STATE")))
                    .forEach(vehicle -> vehicle.put("active", "N"));
            }
        });

        // Print the result
        vehicles.forEach(System.out::println);
    }
}
```