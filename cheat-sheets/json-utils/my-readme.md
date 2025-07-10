Yes, there are several Java-based third-party libraries that can **flatten deeply nested JSON** objects into a flat structure (e.g., for CSV export, tabular formats, etc.).

---

### ✅ Recommended Java JSON Flattener Libraries

| Library                                                                           | Description                                                                                                   | Maven Coordinates |
| --------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- | ----------------- |
| **[JsonFlattener (from JSON-java)](https://github.com/wnameless/json-flattener)** | Lightweight and widely used tool to flatten and unflatten JSON. Supports deeply nested structures and arrays. |                   |

````xml
<dependency>
  <groupId>com.github.wnameless</groupId>
  <artifactId>json-flattener</artifactId>
  <version>0.16.4</version>
</dependency>
``` |
| **[Jackson Tree Model](https://github.com/FasterXML/jackson)** | While Jackson itself doesn't offer flattening out-of-the-box, you can build custom flatteners using `JsonNode` and recursion. |
```xml
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.15.3</version>
</dependency>
``` |
| **[Jolt](https://github.com/bazaarvoice/jolt)** | Declarative JSON transformer that can be configured to flatten or remap JSONs. More powerful but also more complex. |
```xml
<dependency>
  <groupId>com.bazaarvoice.jolt</groupId>
  <artifactId>jolt-core</artifactId>
  <version>0.1.1</version>
</dependency>
``` |

---

### ✅ Example using `json-flattener`

```java
import com.github.wnameless.json.flattener.JsonFlattener;
import java.util.Map;

public class FlattenExample {
  public static void main(String[] args) {
    String json = "{ \"id\": 101, \"name\": \"Alice\", \"address\": { \"city\": \"Wonderland\", \"zip\": \"12345\" } }";
    Map<String, Object> flattenedMap = JsonFlattener.flattenAsMap(json);
    flattenedMap.forEach((k, v) -> System.out.println(k + " = " + v));
  }
}
````

**Output:**

```
id = 101
name = Alice
address.city = Wonderland
address.zip = 12345
```

It also handles:

* Arrays (`address.locations[0].zip`)
* Configurable separator (default is `"."`)
* Custom depth limits if needed

---

Would you like:

* A demo project ZIP with flattening + CSV output?
* A wrapper that limits array depth (e.g., `maxArrayDepth=3`) as you mentioned earlier?
