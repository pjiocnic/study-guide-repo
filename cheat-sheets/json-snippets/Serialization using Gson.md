Yes! You can use **Lombok** to eliminate boilerplate code such as constructors, getters, and setters. Here’s how you can modify your Java classes with Lombok and use Gson for JSON serialization.

---

### **1. Add Lombok and Gson Dependencies**
If you're using **Maven**, add these dependencies in `pom.xml`:

```xml
<dependencies>
    <!-- Gson for JSON serialization -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
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
    implementation 'com.google.code.gson:gson:2.10.1'
    compileOnly 'org.projectlombok:lombok:1.18.30'
    annotationProcessor 'org.projectlombok:lombok:1.18.30'
}
```

---

### **2. Define Java Classes with Lombok**
```java
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
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
    @SerializedName("data")
    private String data;
}

// Wrapper class for the list
@Data
@NoArgsConstructor
class DataListWrapper {
    @SerializedName("dataList")
    private List<DataItem> dataList = new ArrayList<>();

    public void addData(String data) {
        dataList.add(new DataItem(data));
    }
}
```

---

### **3. Serialize to JSON using Gson**
```java
public class GsonLombokExample {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Create the wrapper object
        DataListWrapper dataListWrapper = new DataListWrapper();
        dataListWrapper.addData("1234");
        dataListWrapper.addData("23456");

        // Convert Java object to JSON string
        String jsonOutput = gson.toJson(dataListWrapper);
        System.out.println(jsonOutput);
    }
}
```

---

### **4. Expected JSON Output**
```json
{"dataList":[{"data":"1234"},{"data":"23456"}]}
```

---

### **Benefits of Using Lombok**
✅ Eliminates **getter, setter, constructor** boilerplate
✅ `@Data` provides **getters, setters, `toString()`, `equals()`, and `hashCode()`**
✅ `@AllArgsConstructor` and `@NoArgsConstructor` simplify object creation

Let me know if you need further enhancements, like **pretty-printing JSON**! 🚀