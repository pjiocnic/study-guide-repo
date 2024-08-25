Sure, here's an example in Java that demonstrates how you can create a hierarchy of handlers based on an input request's token:


```java
import java.util.HashMap;
import java.util.Map;

// Abstract base class for handlers
abstract class Handler {
    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract Object handleRequest(String token);
}

// Concrete handler for producing Object A
class HandlerA extends Handler {
    @Override
    public Object handleRequest(String token) {
        if (token.equals("A")) {
            return new ObjectA();
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(token);
        }
        return null;
    }
}

// Concrete handler for producing Object B
class HandlerB extends Handler {
    @Override
    public Object handleRequest(String token) {
        if (token.equals("B")) {
            return new ObjectB();
        } else if (nextHandler != null) {
            return nextHandler.handleRequest(token);
        }
        return null;
    }
}

// Concrete handler that extends HandlerB for producing Object C
class HandlerC extends HandlerB {
    @Override
    public Object handleRequest(String token) {
        if (token.equals("C")) {
            return new ObjectC();
        } else {
            return super.handleRequest(token);
        }
    }
}

```

```java
// Example objects
class ObjectA {}
class ObjectB {}
class ObjectC extends ObjectB {}

public class HandlerExample {
  
    private static Map<String, Handler> handlerMap = new HashMap<>();

    static {
        HandlerA handlerA = new HandlerA();
        HandlerB handlerB = new HandlerB();
        HandlerC handlerC = new HandlerC();

        handlerA.setNextHandler(handlerB);
        handlerB.setNextHandler(handlerC);

        handlerMap.put("A", handlerA);
        handlerMap.put("B", handlerB);
        handlerMap.put("C", handlerC);
    }

    public static void main(String[] args) {
        String token = "C"; // Change this to test different tokens
        Handler handler = handlerMap.get(token.substring(0, 1));
        Object result = handler.handleRequest(token);
        System.out.println("Result: " + result);
    }
}

```


In this example, we have an abstract base class Handler and three concrete handler classes: HandlerA,
HandlerB, and HandlerC. Each handler is responsible for producing a specific type of object based on the input token.

The HandlerA class produces ObjectA when the token is "A", HandlerB produces ObjectB when the token is "B", and HandlerC (which extends HandlerB) produces ObjectC when the token is "C".

The handlers are chained together using the setNextHandler method, allowing them to delegate the request to the next handler in the chain if they cannot handle the current token.

In the main method, we create instances of the handlers and store them in a Map for easy retrieval based on the first character of the token. We then retrieve the appropriate handler from the map, call its
handleRequest method with the token, and print the result.

You can modify the token in the main method to test different scenarios. For example, if you change the token to "A", the output will be Result: ObjectA@hashcode, and if you change it to "B", the output will be
Result: ObjectB@hashcode
