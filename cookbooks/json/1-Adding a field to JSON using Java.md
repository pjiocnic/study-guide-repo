```java
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;

public class JsonModifier {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Load original JSON
        File inputFile = new File("original.json");
        JsonNode root = mapper.readTree(inputFile);

        if (root instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) root;

            // Add a new field at root
            objectNode.put("myExtraField", "someValue");

            // (Optional) Add nested field:
            // ((ObjectNode)objectNode.get("someNestedObject")).put("nestedExtra", 123);

            // Write modified JSON to new file
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("modified.json"), objectNode);
        }
    }
}
```