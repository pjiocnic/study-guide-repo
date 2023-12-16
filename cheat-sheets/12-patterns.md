# COR

1. https://www.digitalocean.com/community/tutorials/chain-of-responsibility-design-pattern-in-java
1. https://springframework.guru/gang-of-four-design-patterns/chain-of-responsibility-pattern/
1. https://www.baeldung.com/chain-of-responsibility-pattern
1. [Implementation of the Chain of Responsibility Design Pattern in Java with multiple children / Real world complex workflow example](https://www.linkedin.com/pulse/implementation-chain-responsibility-design-pattern-java-fatih-tepekoy/)
1. https://docs.spring.io/spring-security/reference/servlet/architecture.html
1. https://kasunprageethdissanayake.medium.com/spring-security-the-security-filter-chain-2e399a1cb8e3

## Example from CHATGPT

```java
// Handler interface
interface Handler {
    void setNextHandler(Handler nextHandler);
    void handleRequest(Request request);
}

// Request class
class Request {
    private String type;

    public Request(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

// Concrete Handlers
class ConcreteHandler1 implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getType().equals("Type1")) {
            System.out.println("ConcreteHandler1 handling Type1 request");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class ConcreteHandler2 implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getType().equals("Type2")) {
            System.out.println("ConcreteHandler2 handling Type2 request");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class ConcreteHandler3 implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getType().equals("Type3")) {
            System.out.println("ConcreteHandler3 handling Type3 request");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

// Client class
public class Client {
    public static void main(String[] args) {
        // Creating the chain
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();

        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        // Making requests
        Request request1 = new Request("Type1");
        Request request2 = new Request("Type2");
        Request request3 = new Request("Type3");

        // Handling requests
        handler1.handleRequest(request1);
        handler1.handleRequest(request2);
        handler1.handleRequest(request3);
    }
}
```