
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

# JSON to Java

1. [Generate a Java Class From JSON](https://www.baeldung.com/java-generate-class-from-json)
1. [Converting JSON to POJOs Using Java](https://dzone.com/articles/converting-json-to-pojos-using-java)

https://signin.aws.amazon.com/signin?redirect_uri=https%3A%2F%2Fus-east-1.console.aws.amazon.com%2Fstates%2Fhome%3Fregion%3Dus-east-1%26state%3DhashArgs%2523%252FsampleProjects%26isauthcode%3Dtrue&client_id=arn%3Aaws%3Aiam%3A%3A015428540659%3Auser%2Fstates&forceMobileApp=0&code_challenge=SwDIs8O0-5_Z6BIhTAHm9ZP7S6Q0sPo_-GhmA3AaRoI&code_challenge_method=SHA-256