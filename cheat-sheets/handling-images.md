
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.12.3</version> <!-- Use the latest version -->
</dependency>

<dependency>
    <groupId>org.apache.httpcomponents.client5</groupId>
    <artifactId>httpclient5</artifactId>
    <version>5.1</version> <!-- Use the latest version available -->
</dependency>
```

```java
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.ResponseHandler;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.ContentType;

import java.io.IOException;

public class ApacheHttpClientExample {

    public static void main(String[] args) {
        // The URL you are posting to
        String url = "https://jsonplaceholder.typicode.com/posts";

        // JSON payload
        String jsonPayload = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        // Create an instance of HttpClient
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            // Create the POST request
            HttpPost httpPost = new HttpPost(url);

            // Set the payload as the request entity (body)
            StringEntity requestEntity = new StringEntity(
                    jsonPayload,
                    ContentType.APPLICATION_JSON);
            httpPost.setEntity(requestEntity);

            // Set headers if needed
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // Execute the request and get the response
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity responseEntity = response.getEntity();

                if (responseEntity != null) {
                    // Convert response entity to string
                    String result = EntityUtils.toString(responseEntity);
                    System.out.println("Response content: " + result);
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```


```java
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.ResponseHandler;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.ContentType;

import java.io.IOException;

public class ApacheHttpClientExample {

    public static void main(String[] args) {
        // The URL you are posting to
        String url = "https://example.com/login"; // Replace with actual URL

        // Prepare the login request
        LoginRequest request = new LoginRequest();
        request.setUserName("user123");
        request.setPassword("password123");

        // Create ObjectMapper to convert POJO to JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Create the POST request
            HttpPost httpPost = new HttpPost(url);

            // Convert LoginRequest object to JSON string
            String jsonPayload = objectMapper.writeValueAsString(request);

            // Set the payload as the request entity
            StringEntity requestEntity = new StringEntity(
                    jsonPayload,
                    ContentType.APPLICATION_JSON);
            httpPost.setEntity(requestEntity);

            // Set headers if needed
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // Execute the request and get the response
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity responseEntity = response.getEntity();

                if (responseEntity != null) {
                    // Convert response entity to string
                    String jsonResponse = EntityUtils.toString(responseEntity);

                    // Convert JSON string to LoginResponse object
                    LoginResponse loginResponse = objectMapper.readValue(jsonResponse, LoginResponse.class);

                    // Extract the access token
                    String accessToken = loginResponse.getAccessToken();
                    System.out.println("Access Token: " + accessToken);
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```