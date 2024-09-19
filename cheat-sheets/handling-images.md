```java
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ByteArrayInputStreamExample {
    public static String convertToString(byte[] byteArray) throws IOException {
        // Create a ByteArrayInputStream from the byte array
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);

        // Create an InputStreamReader to read bytes and decode them into characters (UTF-8)
        InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream, StandardCharsets.UTF_8);

        // Use BufferedReader to read the characters line by line (or character by character if needed)
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        // Create a StringBuilder to accumulate the characters
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        // Read line by line and append to the StringBuilder
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        // Close streams
        bufferedReader.close();
        inputStreamReader.close();

        // Return the resulting String
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        try {
            // Example byte array (representing "Hello, World!" in UTF-8)
            byte[] sampleBytes = "Hello, World!".getBytes(StandardCharsets.UTF_8);

            // Convert the byte array to a String using ByteArrayInputStream
            String result = convertToString(sampleBytes);

            System.out.println("Converted String: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```