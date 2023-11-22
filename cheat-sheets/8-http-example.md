
- pom.xml

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.httpcomponents</groupId>
        <artifactId>httpclient</artifactId>
        <version>4.5.13</version> <!-- Use the latest version -->
    </dependency>
</dependencies>

```
- Java

```java
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientExample {
    public static void main(String[] args) {
        // Create an instance of HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // Define the URL you want to send the GET request to
        String url = "https://example.com";

        // Create an HTTP GET request
        HttpGet httpGet = new HttpGet(url);

        try {
            // Execute the GET request
            HttpResponse response = httpClient.execute(httpGet);

            // Get the response entity as a String
            String responseBody = EntityUtils.toString(response.getEntity());

            // Print the response
            System.out.println("Response Code: " + response.getStatusLine().getStatusCode());
            System.out.println("Response Body: " + responseBody);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the HttpClient to release resources
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

```

# Deserialize to custom object

1. pom.xml

```xml
<dependencies>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.13.0</version> <!-- Use the latest version -->
    </dependency>
</dependencies>
```

2. Define CustomObject

```java
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomObject {
    @JsonProperty("field1")
    private String field1;

    @JsonProperty("field2")
    private int field2;

    // Getters and setters
}
```

3. deserialize

```java
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class HttpClientExample {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "https://example.com";
        HttpGet httpGet = new HttpGet(url;

        try {
            HttpResponse response = httpClient.execute(httpGet);
            String responseBody = EntityUtils.toString(response.getEntity());

            // Use Jackson ObjectMapper to deserialize the JSON response into a list of custom objects
            ObjectMapper objectMapper = new ObjectMapper();
            List<CustomObject> customObjects = objectMapper.readValue(responseBody, new TypeReference<List<CustomObject>>() {});

            // Now, you have a list of custom objects
            for (CustomObject customObject : customObjects) {
                System.out.println(customObject.getField1());
                System.out.println(customObject.getField2());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

```

# Other examples

1. [Introduction to the Java HTTP Client](https://openjdk.org/groups/net/httpclient/recipes.html)
1. [How to send HTTP request GET/POST in Java](https://mkyong.com/java/how-to-send-http-request-getpost-in-java/)
1. [Apache HttpClient Example - CloseableHttpClient](https://www.digitalocean.com/community/tutorials/apache-httpclient-example-closeablehttpclient)
1. [Comparison of Java HTTP Clients](https://reflectoring.io/comparison-of-java-http-clients/)

# AWS

1. [Building dynamic Amazon SNS subscriptions for auto scaling container workloads by Mithun Mallick](https://aws.amazon.com/blogs/compute/building-dynamic-amazon-sns-subscriptions-for-auto-scaling-container-workloads/)
1. [Implementing mutual TLS for Java-based AWS Lambda functions by Dhiraj Mahapatro](https://aws.amazon.com/blogs/compute/implementing-mutual-tls-for-java-based-aws-lambda-functions/)