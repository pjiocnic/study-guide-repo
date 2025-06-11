In Java, you can design an enum with error codes and descriptions by defining fields and a constructor within the enum. You can also provide getter methods to access these fields.

Here's an example:

```java
public enum ErrorCode {

    // Define the enum values with error code and description
    INVALID_INPUT(1001, "The input provided is invalid"),
    AUTHENTICATION_FAILED(1002, "Authentication failed"),
    RESOURCE_NOT_FOUND(1003, "Requested resource not found"),
    PERMISSION_DENIED(1004, "Permission denied");

    // Fields for storing error code and description
    private final int code;
    private final String description;

    // Constructor to set the values
    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    // Getter for error code
    public int getCode() {
        return code;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }
}
```

### Usage:

```java
public class Main {
    public static void main(String[] args) {
        ErrorCode errorCode = ErrorCode.INVALID_INPUT;

        // Accessing the code and description
        System.out.println("Error Code: " + errorCode.getCode());
        System.out.println("Description: " + errorCode.getDescription());
    }
}
```

### Output:
```
Error Code: 1001
Description: The input provided is invalid
```

This design provides a clean way to manage error codes and their corresponding descriptions using Java enums.