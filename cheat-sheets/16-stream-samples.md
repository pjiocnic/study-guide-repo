
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
