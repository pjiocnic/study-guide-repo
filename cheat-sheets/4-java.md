
1. How to extract contiguous digits from a sentence?

```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractDigitsFromSentence {
    public static void main(String[] args) {
        // Input sentence
        String sentence = "The price is $25.99 and the discount is 10%.";

        // Define the regular expression pattern
        Pattern pattern = Pattern.compile("\\d+"); // This matches one or more digits

        // Create a Matcher object
        Matcher matcher = pattern.matcher(sentence);

        // Iterate through the matches and extract the digits
        while (matcher.find()) {
            String digits = matcher.group(); // Get the matched digits as a string
            System.out.println("Found digits: " + digits);
        }
    }
}
```

# Java Examples for Coding Best Practices

1. [Powertools for AWS Lambda (Java)](https://github.com/aws-powertools/powertools-lambda-java/tree/main)
1. [Simplifying serverless best practices with AWS Lambda Powertools Java by Pankaj Agrawal](https://aws.amazon.com/blogs/opensource/simplifying-serverless-best-practices-with-aws-lambda-powertools-java/)
1. [Java on AWS Immersion Day](https://github.com/aws-samples/java-on-aws/tree/main)
- Has some ObjectMapper usage example
1. [Project uses new Lambda/Generic features of Java](https://github.com/aws-samples/aws-lambda-servlet/blob/main/src/main/java/com/aws/samples/lambda/servlet/LambdaWebServletProcessor.java)
- Try.run
- mapTry
- List.ofAll(newServlets).appendAll(existingServlets).distinct();
- Method references

# JSON to Java

1. [Generate a Java Class From JSON](https://www.baeldung.com/java-generate-class-from-json)
1. [Converting JSON to POJOs Using Java](https://dzone.com/articles/converting-json-to-pojos-using-java)

# Java examples by AWS

1. [Mutual TLS for Java based AWS Lambda functions](https://github.com/aws-samples/serverless-mutual-tls/tree/main/software)
1. [AWS SDK for Java](https://github.com/aws/aws-sdk-java/tree/master)
1. [AWS SDK for Java Documentation](https://docs.aws.amazon.com/sdk-for-java/index.html)
1. [[MY NEXT] AWS SDK for Java (v2) code examples](https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/javav2)
1. [Managing Dependencies with AWS SDK for Java – Bill of Materials module (BOM) by Manikandan Subramanian ](https://aws.amazon.com/blogs/developer/managing-dependencies-with-aws-sdk-for-java-bill-of-materials-module-bom/)

