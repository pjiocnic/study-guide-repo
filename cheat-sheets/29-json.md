1. How to format JSON?

In Java, when using the Jackson library for JSON processing, you can use the writeValueAsString() method from the ObjectMapper class to serialize an object into a single-line JSON string.

Here's an example of how you can achieve this:

```java
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonLogger {
    public static void main(String[] args) {
        // Create an example JSON object
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("name", "John Doe");
        jsonObject.put("age", 30);
        jsonObject.put("email", "john.doe@example.com");

        // Create an instance of ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Serialize the JSON object into a single-line string
            String jsonString = objectMapper.writeValueAsString(jsonObject);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static class JsonObject extends HashMap<String, Object> {
        // Helper class to create a JSON-like object
    }
}
```

In this example, we first create a JsonObject (a simple extension of HashMap) to represent a JSON-like object. We then create an instance of ObjectMapper from the Jackson library.

Next, we use the writeValueAsString() method of the ObjectMapper instance to serialize the JsonObject into a single-line JSON string. This method takes the object to be serialized as an argument and returns the JSON string representation.

Finally, we print the resulting JSON string to the console using System.

`out.println(jsonString)`.

The output of this code will be a single-line JSON string:

`{"age":30,"email":"john.doe@example.com","name":"John Doe"}`

If you want to make the JSON output more readable with indentation and line breaks, you can configure the ObjectMapper instance with the enable`(SerializationFeature.INDENT_OUTPUT)` method before serializing the object:

```java
objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
String jsonString = objectMapper.writeValueAsString(jsonObject);
```

This will produce a multi-line JSON output with indentation:

```json
{
  "age" : 30,
  "email" : "john.doe@example.com",
  "name" : "John Doe"
}
```

By using the writeValueAsString() method from the Jackson ObjectMapper class, you can easily serialize Java objects into JSON strings, either in a single line or with additional formatting options as desired.