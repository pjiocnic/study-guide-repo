In an AWS Lambda function written in Java, you can use the AWS Lambda Runtime interface for Java (also known as the AWS Lambda Custom Runtime for Java) to handle events, including SQS events. To transform a JSON message from an SQS event into a custom POJO (Plain Old Java Object), you can follow these steps:

1. Create a POJO representing your custom object:

Define a Java class that represents the structure of the JSON message. Make sure the fields of the class match the structure of the JSON message.

```java

public class MyCustomObject {
    private String field1;
    private int field2;

    // Getters and setters...
}
```

2. Implement the Lambda function:

Create your Lambda function handler and use Jackson (a popular JSON library for Java) to deserialize the JSON message into your custom object.

```java

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyLambdaFunction implements RequestHandler<SQSEvent, Void> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        for (SQSEvent.SQSMessage message : event.getRecords()) {
            try {
                // Deserialize JSON message into your custom object
                MyCustomObject myObject = objectMapper.readValue(message.getBody(), MyCustomObject.class);

                // Process the custom object as needed
                System.out.println("Received custom object: " + myObject.getField1());

            } catch (Exception e) {
                // Handle exception (e.g., log the error)
                e.printStackTrace();
            }
        }

        return null;
    }
}
```

In this example, the objectMapper is used to deserialize the JSON message from SQS into an instance of MyCustomObject.

3. Add Jackson Dependency:

Ensure you include the Jackson dependency in your project. If you're using Maven, you can add the following dependency to your pom.xml file:

```xml

<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.13.1</version> <!-- Use the latest version -->
</dependency>
```

4. Deploy and Configure Your Lambda Function:

Deploy your Lambda function, and configure it to receive events from the SQS queue.

Make sure that the Lambda function's IAM role has the necessary permissions to receive messages from the SQS queue.

With these steps, your Lambda function should be able to handle SQS events, deserialize the JSON messages into your custom POJO, and process the data accordingly.