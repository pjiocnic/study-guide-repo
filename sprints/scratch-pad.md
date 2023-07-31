
```java
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyJsonProperties {

    private final ResourcePatternResolver resourcePatternResolver;

    private Map<String, String> jsonData;

    @Autowired
    public MyJsonProperties(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    @PostConstruct
    private void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = new HashMap<>();

        // Define the pattern for JSON files on the classpath
        String pattern = "classpath*:/data*.json";

        // Get all resources matching the pattern
        Resource[] resources = resourcePatternResolver.getResources(pattern);

        for (Resource resource : resources) {
            Map<String, String> data = objectMapper.readValue(resource.getInputStream(), Map.class);
            jsonData.putAll(data);
        }
    }

    public String getProperty(String key) {
        return jsonData.get(key);
    }
}
```