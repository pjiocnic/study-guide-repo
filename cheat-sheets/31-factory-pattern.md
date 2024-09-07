# Factory implementations by ChatGPT

1. Approach 1

```java
import java.util.HashMap;
import java.util.Map;

// Interface for creating different types of products
interface Product {
    void doSomething();
}

// Concrete implementation of Product
class ConcreteProductA implements Product {
    @Override
    public void doSomething() {
        System.out.println("ConcreteProductA is doing something.");
    }
}

// Concrete implementation of Product
class ConcreteProductB implements Product {
    @Override
    public void doSomething() {
        System.out.println("ConcreteProductB is doing something.");
    }
}

// Factory class responsible for creating products
class ProductFactory {
    private static final Map<String, Class<? extends Product>> productMap = new HashMap<>();

    static {
        productMap.put("A", ConcreteProductA.class);
        productMap.put("B", ConcreteProductB.class);
        // Add more product types here as needed
    }

    // Parametrized factory method to create different types of products
    public static Product createProduct(String type) throws IllegalAccessException, InstantiationException {
        Class<? extends Product> productClass = productMap.get(type);
        if (productClass == null) {
            throw new IllegalArgumentException("Invalid product type.");
        }
        return productClass.newInstance();
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            // Creating products using the factory method
            Product productA = ProductFactory.createProduct("A");
            productA.doSomething();

            Product productB = ProductFactory.createProduct("B");
            productB.doSomething();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
```

2. Approach 2

```java
import java.util.HashMap;
import java.util.Map;

// Interface for creating different types of products
interface Product {
    void doSomething();
}

// Concrete implementation of Product
class ConcreteProductA implements Product {
    @Override
    public void doSomething() {
        System.out.println("ConcreteProductA is doing something.");
    }
}

// Concrete implementation of Product
class ConcreteProductB implements Product {
    @Override
    public void doSomething() {
        System.out.println("ConcreteProductB is doing something.");
    }
}

// Factory class responsible for creating products
class ProductFactory {
    private static final Map<String, Class<? extends Product>> productMap = new HashMap<>();

    static {
        productMap.put("A", ConcreteProductA.class);
        productMap.put("B", ConcreteProductB.class);
        // Add more product types here as needed
    }

    // Parametrized factory method to create different types of products
    public static Product createProduct(String type) throws IllegalAccessException, InstantiationException {
        Class<? extends Product> productClass = productMap.get(type);
        if (productClass == null) {
            throw new IllegalArgumentException("Invalid product type.");
        }
        return productClass.newInstance();
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            // Creating products using the factory method
            Product productA = ProductFactory.createProduct("A");
            productA.doSomething();

            Product productB = ProductFactory.createProduct("B");
            productB.doSomething();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

```

3. Project structure

```java
product-factory-library/
    src/
        main/
            java/
                com/
                    yourcompany/
                        productfactory/
                            Product.java
                            ConcreteProductA.java
                            ConcreteProductB.java
                            ProductFactory.java
        test/
            java/  (optional, for unit tests)
    pom.xml  (if using Maven) or build.gradle (if using Gradle)
```

4. Approach 3: Using interfaces

- Define interfaces for the factory and products in your library

```java

// Interface for creating different types of products
public interface Product {
    void doSomething();
}

// Interface for the factory
public interface ProductFactory {
    Product createProduct(String type);
}
```

- Client application code using factory

```java
// Import the interfaces from the factory library

// Client class that uses the factory to create products
public class Client {
    private final ProductFactory productFactory;

    // Use dependency injection to inject the factory implementation
    public Client(ProductFactory productFactory) {
        this.productFactory = productFactory;
    }

    // Method that uses the factory to create and use products
    public void useProducts() {
        // Use the factory to create products
        Product productA = productFactory.createProduct("A");
        Product productB = productFactory.createProduct("B");

        // Use the products through their interfaces
        productA.doSomething();
        productB.doSomething();
    }
}

```

# Useful referenances

1. [Head First Design Patterns (2020 2nd Edition)](https://github.com/bethrobson/Head-First-Design-Patterns/tree/master/src/headfirst/designpatterns/factory)