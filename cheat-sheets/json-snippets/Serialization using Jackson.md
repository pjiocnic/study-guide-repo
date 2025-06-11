<h1>Serialization using Jackson</h1>

Yes! You can use **FasterXML Jackson** (`com.fasterxml.jackson.databind.ObjectMapper`), which is often faster and more flexible than Gson. Below is the equivalent implementation using **Jackson 2.12** with **Lombok**.

---

### **1. Add Dependencies**
If you're using **Maven**, add these dependencies to `pom.xml`:

```xml
<dependencies>
    <!-- Jackson for JSON serialization -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.12.7</version>
    </dependency>

    <!-- Lombok to reduce boilerplate code -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

For **Gradle**, add:
```gradle
dependencies {
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.12.7'
    compileOnly 'org.projectlombok:lombok:1.18.30'
    annotationProcessor 'org.projectlombok:lombok:1.18.30'
}
```

---

### **2. Define Java Classes with Lombok**
```java
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// Represents a single data item
@Data
@AllArgsConstructor
@NoArgsConstructor
class DataItem {
    @JsonProperty("data") // Maps field to JSON key
    private String data;
}

// Wrapper class for the list
@Data
@NoArgsConstructor
class DataListWrapper {
    @JsonProperty("dataList") // Ensures correct JSON key name
    private List<DataItem> dataList = new ArrayList<>();

    public void addData(String data) {
        dataList.add(new DataItem(data));
    }
}
```

---

### **3. Serialize to JSON using Jackson**
```java
public class JacksonLombokExample {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper

            // Create the wrapper object
            DataListWrapper dataListWrapper = new DataListWrapper();
            dataListWrapper.addData("1234");
            dataListWrapper.addData("23456");

            // Convert Java object to JSON string
            String jsonOutput = objectMapper.writeValueAsString(dataListWrapper);
            System.out.println(jsonOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

### **4. Expected JSON Output**
```json
{"dataList":[{"data":"1234"},{"data":"23456"}]}
```

---

### **Benefits of Using Jackson**
✅ **Faster than Gson** for large datasets
✅ Supports **custom serialization/deserialization** easily
✅ Works well with **Spring Boot and REST APIs**
✅ **More flexible mappings** with annotations like `@JsonProperty`

Would you like **pretty-printing** or any custom serialization tweaks? 🚀