
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