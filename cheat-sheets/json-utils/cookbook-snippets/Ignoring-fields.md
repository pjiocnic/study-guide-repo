Perfect — Jackson 2.13.5 (from FasterXML) fully supports the solution outlined earlier. Here's a refined, production-ready setup tailored for **Jackson 2.13.5**:

---

## ✅ Use Case Recap

You have a POJO that:

* Deserializes JSON **without** certain fields.
* You want to **add a new field** in the class.
* This new field should:

  * ✅ Be **ignored** when **reading** the JSON (deserialization),
  * ✅ Be **included** when **writing** the JSON (serialization),
  * ✅ Be manually set in Java code before saving.

---

## ✅ Full Jackson-Compatible POJO

```java
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true) // Ignore any unexpected fields in JSON
public class Person {

    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL) // Only serialize if non-null
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String sourceTag; // You will set this in Java manually

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Can deserialize but won't appear in output
    private String internalId;

    // --- Getters and Setters ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSourceTag() { return sourceTag; }
    public void setSourceTag(String sourceTag) { this.sourceTag = sourceTag; }

    public String getInternalId() { return internalId; }
    public void setInternalId(String internalId) { this.internalId = internalId; }
}
```

---

## ✅ Usage Example (Test Class)

```java
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJacksonEnrichment {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Input JSON (missing sourceTag and internalId)
        String inputJson = "{\"name\": \"Alice\"}";

        // Deserialize
        Person person = mapper.readValue(inputJson, Person.class);

        // Add new value manually
        person.setSourceTag("enriched-v1");
        person.setInternalId("secret123"); // Will not be in output

        // Serialize
        String enrichedJson = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(person);

        System.out.println(enrichedJson);
    }
}
```

---

## ✅ Output (as expected)

```json
{
  "name" : "Alice",
  "sourceTag" : "enriched-v1"
}
```

---

## 🧠 Summary of Annotations

| Annotation                                    | Purpose                                        |
| --------------------------------------------- | ---------------------------------------------- |
| `@JsonProperty(access = Access.READ_WRITE)`   | Allows manual setting + serialization          |
| `@JsonProperty(access = Access.WRITE_ONLY)`   | Allows deserialization only, hides from output |
| `@JsonInclude(Include.NON_NULL)`              | Omits nulls during serialization               |
| `@JsonIgnoreProperties(ignoreUnknown = true)` | Ignores unexpected fields in input JSON        |

---

Let me know if you're writing the enriched JSON to a file or want to handle lists of objects this way.
