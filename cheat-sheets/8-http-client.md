
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

# 1. Deserialize to custom object

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

4. If you need to convert response body to pojo

```java
import com.fasterxml.jackson.databind.ObjectMapper;

// ...

public class HttpClientExample {

    // ...

    public static void main(String[] args) {
        HttpClient httpClient = null;
        try {
            // ...

            // Send the request and handle the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the request was successful (status code 2xx)
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                // Parse the response body into a custom object (assuming it's JSON)
                ObjectMapper objectMapper = new ObjectMapper();
                YourCustomObject customObject = objectMapper.readValue(response.body(), YourCustomObject.class);

                // Now you can work with the custom object
                System.out.println("Custom Object: " + customObject);
            } else {
                // Handle error responses
                System.err.println("Error response: " + response.statusCode() + " " + response.body());
            }

            // ...
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        } finally {
            // ...
        }
    }
}

```

# 2. Using with Lambda

1. Initializing

public static readonly HttpClient client = new HttpClient();

initialised at the top of my code before any functions

2. https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/lambda-optimize-starttime.html

3. https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/http-configuration-url.html

# 3. JDK 11

1. [Java 11 Network API: Use HttpClient for Synchronous HTTP Communication](https://learning.oreilly.com/scenarios/java-11-network/9781492081234/)
1. [[JDK 11] Java HttpClient Connection Management](https://www.baeldung.com/java-httpclient-connection-management)
1. [[JDK 11] Java HttpClient Timeout](https://www.baeldung.com/java-httpclient-timeout)
1. [[JDK 11] Posting with Java HttpClient By Baeldung](https://www.baeldung.com/java-httpclient-post)
1. [[JDK 11] Core Java, Volume II--Advanced Features, 11th Edition Cay S. Horstmann](https://learning.oreilly.com/library/view/core-java-volume/9780135167175/ch04.xhtml#ch4lev5)
- How to turn on http logging
1. [[MUST SEE] 10 Java Networking](https://docs.oracle.com/en/java/javase/17/core/java-networking.html#GUID-E6C82625-7C02-4AB3-B15D-0DF8A249CD73)
1. [Introduction to the Java HTTP Client](https://openjdk.org/groups/net/httpclient/recipes.html)
1. [How to send HTTP request GET/POST in Java](https://mkyong.com/java/how-to-send-http-request-getpost-in-java/)
1. [API Docs](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html)

# 4. JDK 9

1. [Exploring the New HTTP Client in Java](https://www.baeldung.com/java-9-http-client)

# 5. Apache Client

1. [Apache HttpClient Example - CloseableHttpClient](https://www.digitalocean.com/community/tutorials/apache-httpclient-example-closeablehttpclient)

# 6. Comparisons

1. [Comparison of Java HTTP Clients](https://reflectoring.io/comparison-of-java-http-clients/)
- HttpClient included from Java 11
- Apache HTTPClient from Apache HttpComponents project
- OkHttpClient from Square
- Spring WebClient for Spring Boot applications
1. [Lesson I have learned from using JDK11 HTTP Client By Maxim Kirilov](https://medium.com/@kir.maxim/lesson-i-have-learned-from-using-jdk11-http-client-2cf990daba03)

# 7. AWS

1. [Introducing AWS Common Runtime HTTP Client in the AWS SDK for Java 2.x by Zoe Wang](https://aws.amazon.com/blogs/developer/introducing-aws-common-runtime-http-client-in-the-aws-sdk-for-java-2-x/)
1. [Building dynamic Amazon SNS subscriptions for auto scaling container workloads by Mithun Mallick](https://aws.amazon.com/blogs/compute/building-dynamic-amazon-sns-subscriptions-for-auto-scaling-container-workloads/)
1. [Implementing mutual TLS for Java-based AWS Lambda functions by Dhiraj Mahapatro](https://aws.amazon.com/blogs/compute/implementing-mutual-tls-for-java-based-aws-lambda-functions/)

# 8. Jitters and Retries

1. [Better Retries with Exponential Backoff and Jitter](https://www.baeldung.com/resilience4j-backoff-jitter)
1. [Retries](https://encore.dev/blog/retries)

# 9. Error Handling

https://codereview.stackexchange.com/questions/11323/httpclient-error-handling