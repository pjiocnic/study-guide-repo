<h1>Spring/Java FAQs</h1>

<!-- TOC -->

- [1. How to load multiple configs in spring](#1-how-to-load-multiple-configs-in-spring)
- [Fat jar using spring](#fat-jar-using-spring)

<!-- /TOC -->

# 1. How to load multiple configs in spring

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

# Fat jar using spring

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.2.4</version> <!-- Use the latest version -->
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                    <configuration>
                        <transformers>
                            <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                <mainClass>your.package.YourMainClass</mainClass>
                            </transformer>
                        </transformers>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>


```