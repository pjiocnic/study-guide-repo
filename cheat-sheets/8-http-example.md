
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

2. Method 2


```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HttpClientExample {

    public static void main(String[] args) {
        HttpClient httpClient = null;
        try {
            // Create HttpClient
            httpClient = HttpClient.newHttpClient();

            // Define the base URL
            String baseUrl = "https://example.com/api";

            // Create parameters
            Map<Object, Object> params = new HashMap<>();
            params.put("param1", "value1");
            params.put("param2", "value2");

            // Build the URL with parameters
            String urlWithParams = buildUrlWithParams(baseUrl, params);

            // Create HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(urlWithParams))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .build();

            // Send the request and handle the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

            // Print response headers
            HttpHeaders headers = response.headers();
            headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        } finally {
            // Close the HttpClient in the finally block
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }

    private static String buildUrlWithParams(String baseUrl, Map<Object, Object> params) {
        StringBuilder stringBuilder = new StringBuilder(baseUrl);
        if (!params.isEmpty()) {
            stringBuilder.append("?");
            params.forEach((key, value) -> {
                stringBuilder.append(URLEncoder.encode(key.toString(), StandardCharsets.UTF_8));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(value.toString(), StandardCharsets.UTF_8));
                stringBuilder.append("&");
            });
            stringBuilder.deleteCharAt(stringBuilder.length() - 1); // Remove the trailing "&"
        }
        return stringBuilder.toString();
    }
}


```

3. Method 3: Sending parameters in the body

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class HttpClientExample {

    public static void main(String[] args) {
        HttpClient httpClient = null;
        try {
            // Create HttpClient
            httpClient = HttpClient.newHttpClient();

            // Define the base URL
            String baseUrl = "https://example.com/api";

            // Create parameters
            Map<Object, Object> params = new HashMap<>();
            params.put("param1", "value1");
            params.put("param2", "value2");

            // Create HttpRequest with parameters in the body
            HttpRequest request = buildPostRequest(baseUrl, params);

            // Send the request and handle the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

            // Print response headers
            HttpHeaders headers = response.headers();
            headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        } finally {
            // Close the HttpClient in the finally block
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }

    private static HttpRequest buildPostRequest(String baseUrl, Map<Object, Object> params) {
        // Build the URL
        String url = baseUrl;

        // Build the request body
        StringBuilder requestBody = new StringBuilder();
        params.forEach((key, value) -> {
            requestBody.append(URLEncoder.encode(key.toString(), StandardCharsets.UTF_8));
            requestBody.append("=");
            requestBody.append(URLEncoder.encode(value.toString(), StandardCharsets.UTF_8));
            requestBody.append("&");
        });
        requestBody.deleteCharAt(requestBody.length() - 1); // Remove the trailing "&"

        // Create HttpRequest with parameters in the body
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();
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