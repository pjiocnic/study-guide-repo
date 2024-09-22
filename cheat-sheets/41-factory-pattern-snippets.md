# Problem

I have the following

```java
abstract IngredientHandler has create and update

SpinachHandler extends IngredientHandler
EggPlantHandler extends IngredientHandler

MozzarellaCheeseHandler extends CheeseHandler which extends IngredientHandler
PepperJackCheeseHandler extends CheeseHandler which extends IngredientHandler
```

Depending on the type of order Veggie vs Pepperoni, PizzaHandler creates a VeggiePizza or PepperoniPizza

Lets say a VeggiePizza is made up of Spinach, EggPlant and PepperJackCheese Ingredients or
a PepperoniPizza is made up of Spinach, EggPlant and MozzarellaCheese Ingredients

If the order is VeggiePizza, PizzaHandler creates SpinachHandler, EggPlantHandler and PepperJackHandler. On the other hand, if the order is a PepperoniPizza order, PizzaHandler creates SpinachHandler, EggPlantHandler and PepperJackCheeseHandler

How can I implement a scalable solution using Java?

# Solution

To implement a scalable solution for this pizza ordering system in Java, we can use a combination of design patterns, including the Factory Method pattern, the Builder pattern, and the Strategy pattern. This approach will allow for easy extension of pizza types and ingredients in the future. Here's a detailed implementation: [1]

First, let's define our ingredient interfaces and abstract classes:

```java
public interface Ingredient {
    String getName();
}

public abstract class IngredientHandler {
    protected Ingredient ingredient;

    public abstract Ingredient create();
    public abstract void update(Ingredient ingredient);
}

public abstract class CheeseHandler extends IngredientHandler {
    // Additional cheese-specific methods can be added here
}

```

Now, let's implement our concrete ingredients and handlers:

```java
public class Spinach implements Ingredient {
    public String getName() { return "Spinach"; }
}

public class EggPlant implements Ingredient {
    public String getName() { return "EggPlant"; }
}

public class MozzarellaCheese implements Ingredient {
    public String getName() { return "Mozzarella Cheese"; }
}

public class PepperJackCheese implements Ingredient {
    public String getName() { return "Pepper Jack Cheese"; }
}

```

```java
public class SpinachHandler extends IngredientHandler {
    public Ingredient create() {
        ingredient = new Spinach();
        return ingredient;
    }

    public void update(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}

public class EggPlantHandler extends IngredientHandler {
    public Ingredient create() {
        ingredient = new EggPlant();
        return ingredient;
    }

    public void update(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}

public class MozzarellaCheeseHandler extends CheeseHandler {
    public Ingredient create() {
        ingredient = new MozzarellaCheese();
        return ingredient;
    }

    public void update(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}

public class PepperJackCheeseHandler extends CheeseHandler {
    public Ingredient create() {
        ingredient = new PepperJackCheese();
        return ingredient;
    }

    public void update(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
```

Next, let's define our Pizza interface and abstract class:

```java
public interface Pizza {
    void addIngredient(Ingredient ingredient);
    List<Ingredient> getIngredients();
}

public abstract class AbstractPizza implements Pizza {
    protected List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}

```


Implement concrete Pizza classes:

```java
public class VeggiePizza extends AbstractPizza {}

public class PepperoniPizza extends AbstractPizza {}
```

Create a PizzaBuilder to construct pizzas:

```java
public class PizzaBuilder {
    private Pizza pizza;
    private List<IngredientHandler> handlers = new ArrayList<>();

    public PizzaBuilder(Pizza pizza) {
        this.pizza = pizza;
    }

    public PizzaBuilder addHandler(IngredientHandler handler) {
        handlers.add(handler);
        return this;
    }

    public Pizza build() {
        for (IngredientHandler handler : handlers) {
            pizza.addIngredient(handler.create());
        }
        return pizza;
    }
}
```

Implement a PizzaFactory to create different types of pizzas:

```java
public class PizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza;
        PizzaBuilder builder;

        switch (type.toLowerCase()) {
            case "veggie":
                pizza = new VeggiePizza();
                builder = new PizzaBuilder(pizza)
                    .addHandler(new SpinachHandler())
                    .addHandler(new EggPlantHandler())
                    .addHandler(new PepperJackCheeseHandler());
                break;
            case "pepperoni":
                pizza = new PepperoniPizza();
                builder = new PizzaBuilder(pizza)
                    .addHandler(new SpinachHandler())
                    .addHandler(new EggPlantHandler())
                    .addHandler(new MozzarellaCheeseHandler());
                break;
            default:
                throw new IllegalArgumentException("Unknown pizza type: " + type);
        }

        return builder.build();
    }
}
```

Finally, let's create a PizzaHandler to manage pizza orders:

```java
public class PizzaHandler {
    private PizzaFactory pizzaFactory = new PizzaFactory();

    public Pizza createPizza(String type) {
        return pizzaFactory.createPizza(type);
    }

    public void displayPizzaIngredients(Pizza pizza) {
        System.out.println("Pizza ingredients:");
        for (Ingredient ingredient : pizza.getIngredients()) {
            System.out.println("- " + ingredient.getName());
        }
    }
}
```


Here's how you can use this system:

```java
public class PizzaOrderSystem {
    public static void main(String[] args) {
        PizzaHandler handler = new PizzaHandler();

        Pizza veggiePizza = handler.createPizza("veggie");
        System.out.println("Veggie Pizza:");
        handler.displayPizzaIngredients(veggiePizza);

        System.out.println();

        Pizza pepperoniPizza = handler.createPizza("pepperoni");
        System.out.println("Pepperoni Pizza:");
        handler.displayPizzaIngredients(pepperoniPizza);
    }
}

```

**This implementation is scalable and flexible:**

It's easy to add new pizza types by creating a new concrete Pizza class and adding a new case in the PizzaFactory.

New ingredients can be added by creating new Ingredient and IngredientHandler classes.

The composition of each pizza type can be easily modified in the PizzaFactory.

The PizzaBuilder allows for flexible construction of pizzas with different combinations of ingredients.

The use of interfaces and abstract classes allows for easy extension and modification of behavior.

This design follows the Open/Closed Principle, making it easy to extend the system without modifying existing code. It also adheres to the Single Responsibility Principle, with each class having a clear and specific purpose.

**Sources**
[1] [Prepare a Pizza in Java. The Fizzeria is a new pizza outlet and… | by Harshal Mistry](https://medium.com/javarevisited/prepare-a-pizza-in-java-76a263fb35c0)

# To make them Singletons were Possible

Certainly! We can modify our design to incorporate singletons for the handlers and other components where appropriate. This approach can help improve performance and resource usage, especially if the handlers are stateless or their state doesn't change between invocations. Here's how we can adapt our solution:

First, let's modify our IngredientHandler to be a singleton:

```java
public abstract class IngredientHandler {
    protected Ingredient ingredient;

    public abstract Ingredient create();
    public abstract void update(Ingredient ingredient);
}
```

Now, let's implement our concrete handlers as singletons:

```java
public class SpinachHandler extends IngredientHandler {
    private static SpinachHandler instance;

    private SpinachHandler() {}

    public static synchronized SpinachHandler getInstance() {
        if (instance == null) {
            instance = new SpinachHandler();
        }
        return instance;
    }

    public Ingredient create() {
        ingredient = new Spinach();
        return ingredient;
    }

    public void update(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}

public class EggPlantHandler extends IngredientHandler {
    private static EggPlantHandler instance;

    private EggPlantHandler() {}

    public static synchronized EggPlantHandler getInstance() {
        if (instance == null) {
            instance = new EggPlantHandler();
        }
        return instance;
    }

    public Ingredient create() {
        ingredient = new EggPlant();
        return ingredient;
    }

    public void update(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}

public class MozzarellaCheeseHandler extends CheeseHandler {
    private static MozzarellaCheeseHandler instance;

    private MozzarellaCheeseHandler() {}

    public static synchronized MozzarellaCheeseHandler getInstance() {
        if (instance == null) {
            instance = new MozzarellaCheeseHandler();
        }
        return instance;
    }

    public Ingredient create() {
        ingredient = new MozzarellaCheese();
        return ingredient;
    }

    public void update(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}

public class PepperJackCheeseHandler extends CheeseHandler {
    private static PepperJackCheeseHandler instance;

    private PepperJackCheeseHandler() {}

    public static synchronized PepperJackCheeseHandler getInstance() {
        if (instance == null) {
            instance = new PepperJackCheeseHandler();
        }
        return instance;
    }

    public Ingredient create() {
        ingredient = new PepperJackCheese();
        return ingredient;
    }

    public void update(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
```

Update the PizzaFactory to use the singleton instances:

```java
public class PizzaFactory {
    private static PizzaFactory instance;

    private PizzaFactory() {}

    public static synchronized PizzaFactory getInstance() {
        if (instance == null) {
            instance = new PizzaFactory();
        }
        return instance;
    }

    public Pizza createPizza(String type) {
        Pizza pizza;
        PizzaBuilder builder;

        switch (type.toLowerCase()) {
            case "veggie":
                pizza = new VeggiePizza();
                builder = new PizzaBuilder(pizza)
                    .addHandler(SpinachHandler.getInstance())
                    .addHandler(EggPlantHandler.getInstance())
                    .addHandler(PepperJackCheeseHandler.getInstance());
                break;
            case "pepperoni":
                pizza = new PepperoniPizza();
                builder = new PizzaBuilder(pizza)
                    .addHandler(SpinachHandler.getInstance())
                    .addHandler(EggPlantHandler.getInstance())
                    .addHandler(MozzarellaCheeseHandler.getInstance());
                break;
            default:
                throw new IllegalArgumentException("Unknown pizza type: " + type);
        }

        return builder.build();
    }
}
```

Update the PizzaHandler to use the singleton PizzaFactory:

```java
public class PizzaHandler {
    private static PizzaHandler instance;
    private PizzaFactory pizzaFactory;

    private PizzaHandler() {
        this.pizzaFactory = PizzaFactory.getInstance();
    }

    public static synchronized PizzaHandler getInstance() {
        if (instance == null) {
            instance = new PizzaHandler();
        }
        return instance;
    }

    public Pizza createPizza(String type) {
        return pizzaFactory.createPizza(type);
    }

    public void displayPizzaIngredients(Pizza pizza) {
        System.out.println("Pizza ingredients:");
        for (Ingredient ingredient : pizza.getIngredients()) {
            System.out.println("- " + ingredient.getName());
        }
    }
}
```

Finally, update the main method to use the singleton PizzaHandler:

```java
public class PizzaOrderSystem {
    public static void main(String[] args) {
        PizzaHandler handler = PizzaHandler.getInstance();

        Pizza veggiePizza = handler.createPizza("veggie");
        System.out.println("Veggie Pizza:");
        handler.displayPizzaIngredients(veggiePizza);

        System.out.println();

        Pizza pepperoniPizza = handler.createPizza("pepperoni");
        System.out.println("Pepperoni Pizza:");
        handler.displayPizzaIngredients(pepperoniPizza);
    }
}
```

This implementation using singletons offers several benefits: [1]

**Reduced memory usage:** Only one instance of each handler is created and reused.

**Improved performance:** Subsequent calls to get handler instances are faster as they don't need to create new objects.

**Consistent state:** If the handlers need to maintain any state, it will be consistent across all uses of the handler.

However, it's important to note some considerations when using singletons: [2]

**Thread safety:** The singleton implementation shown here uses synchronized methods for thread safety. In a highly concurrent environment, you might want to consider using double-checked locking or initialization-on-demand holder idiom for better performance.

**Testing:** Singletons can make unit testing more challenging. You might need to use dependency injection or create interfaces for your singletons to allow for easier mocking in tests.

**Scalability:** In a distributed system, singletons might not scale well. You'd need to consider other patterns for such scenarios.

This singleton-based design maintains the flexibility and extensibility of the original design while potentially improving performance for repeated pizza creation operations.

# Testing

Certainly! I'll provide JUnit tests for the key components of our pizza ordering system. We'll use JUnit 5 for these tests. Here are some example test classes:

**Test for IngredientHandlers:**

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IngredientHandlerTest {

    @Test
    void testSpinachHandler() {
        SpinachHandler handler = SpinachHandler.getInstance();
        Ingredient ingredient = handler.create();

        assertNotNull(ingredient);
        assertEquals("Spinach", ingredient.getName());
        assertSame(SpinachHandler.getInstance(), handler, "SpinachHandler should be a singleton");
    }

    @Test
    void testEggPlantHandler() {
        EggPlantHandler handler = EggPlantHandler.getInstance();
        Ingredient ingredient = handler.create();

        assertNotNull(ingredient);
        assertEquals("EggPlant", ingredient.getName());
        assertSame(EggPlantHandler.getInstance(), handler, "EggPlantHandler should be a singleton");
    }

    @Test
    void testMozzarellaCheeseHandler() {
        MozzarellaCheeseHandler handler = MozzarellaCheeseHandler.getInstance();
        Ingredient ingredient = handler.create();

        assertNotNull(ingredient);
        assertEquals("Mozzarella Cheese", ingredient.getName());
        assertSame(MozzarellaCheeseHandler.getInstance(), handler, "MozzarellaCheeseHandler should be a singleton");
    }

    @Test
    void testPepperJackCheeseHandler() {
        PepperJackCheeseHandler handler = PepperJackCheeseHandler.getInstance();
        Ingredient ingredient = handler.create();

        assertNotNull(ingredient);
        assertEquals("Pepper Jack Cheese", ingredient.getName());
        assertSame(PepperJackCheeseHandler.getInstance(), handler, "PepperJackCheeseHandler should be a singleton");
    }
}
```

**Test for PizzaFactory:**

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PizzaFactoryTest {

    @Test
    void testCreateVeggiePizza() {
        PizzaFactory factory = PizzaFactory.getInstance();
        Pizza pizza = factory.createPizza("veggie");

        assertNotNull(pizza);
        assertTrue(pizza instanceof VeggiePizza);
        assertEquals(3, pizza.getIngredients().size());
        assertTrue(pizza.getIngredients().stream().anyMatch(i -> i.getName().equals("Spinach")));
        assertTrue(pizza.getIngredients().stream().anyMatch(i -> i.getName().equals("EggPlant")));
        assertTrue(pizza.getIngredients().stream().anyMatch(i -> i.getName().equals("Pepper Jack Cheese")));
    }

    @Test
    void testCreatePepperoniPizza() {
        PizzaFactory factory = PizzaFactory.getInstance();
        Pizza pizza = factory.createPizza("pepperoni");

        assertNotNull(pizza);
        assertTrue(pizza instanceof PepperoniPizza);
        assertEquals(3, pizza.getIngredients().size());
        assertTrue(pizza.getIngredients().stream().anyMatch(i -> i.getName().equals("Spinach")));
        assertTrue(pizza.getIngredients().stream().anyMatch(i -> i.getName().equals("EggPlant")));
        assertTrue(pizza.getIngredients().stream().anyMatch(i -> i.getName().equals("Mozzarella Cheese")));
    }

    @Test
    void testCreateInvalidPizza() {
        PizzaFactory factory = PizzaFactory.getInstance();
        assertThrows(IllegalArgumentException.class, () -> factory.createPizza("invalid"));
    }

    @Test
    void testPizzaFactorySingleton() {
        PizzaFactory factory1 = PizzaFactory.getInstance();
        PizzaFactory factory2 = PizzaFactory.getInstance();
        assertSame(factory1, factory2, "PizzaFactory should be a singleton");
    }
}
```

**Test for PizzaHandler:**

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PizzaHandlerTest {

    @Test
    void testCreateVeggiePizza() {
        PizzaHandler handler = PizzaHandler.getInstance();
        Pizza pizza = handler.createPizza("veggie");

        assertNotNull(pizza);
        assertTrue(pizza instanceof VeggiePizza);
        assertEquals(3, pizza.getIngredients().size());
    }

    @Test
    void testCreatePepperoniPizza() {
        PizzaHandler handler = PizzaHandler.getInstance();
        Pizza pizza = handler.createPizza("pepperoni");

        assertNotNull(pizza);
        assertTrue(pizza instanceof PepperoniPizza);
        assertEquals(3, pizza.getIngredients().size());
    }

    @Test
    void testPizzaHandlerSingleton() {
        PizzaHandler handler1 = PizzaHandler.getInstance();
        PizzaHandler handler2 = PizzaHandler.getInstance();
        assertSame(handler1, handler2, "PizzaHandler should be a singleton");
    }
}
```

**Test for PizzaBuilder:**

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PizzaBuilderTest {

    @Test
    void testBuildPizza() {
        Pizza pizza = new VeggiePizza();
        PizzaBuilder builder = new PizzaBuilder(pizza)
            .addHandler(SpinachHandler.getInstance())
            .addHandler(EggPlantHandler.getInstance())
            .addHandler(PepperJackCheeseHandler.getInstance());

        Pizza builtPizza = builder.build();

        assertNotNull(builtPizza);
        assertEquals(3, builtPizza.getIngredients().size());
        assertTrue(builtPizza.getIngredients().stream().anyMatch(i -> i.getName().equals("Spinach")));
        assertTrue(builtPizza.getIngredients().stream().anyMatch(i -> i.getName().equals("EggPlant")));
        assertTrue(builtPizza.getIngredients().stream().anyMatch(i -> i.getName().equals("Pepper Jack Cheese")));
    }
}
```

These tests cover the main functionality of our pizza ordering system:

* They verify that the IngredientHandlers are singletons and create the correct ingredients.

* They test the PizzaFactory's ability to create different types of pizzas with the correct ingredients.

* They ensure the PizzaHandler correctly delegates pizza creation to the PizzaFactory.

* They check that the PizzaBuilder correctly assembles pizzas with the given ingredients.

* They verify that singletons are working as expected for the handlers, factory, and pizza handler.

To run these tests, you'll need to have JUnit 5 in your project's classpath. If you're using a build tool like Maven, you can add the following dependency to your pom.xml: [1]

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.8.2</version>
    <scope>test</scope>
</dependency>
```

These tests provide good coverage of the system's core functionality. However, in a real-world scenario, you might want to add more edge cases, test for concurrent access to singletons, and possibly use mocking frameworks to isolate units of code for testing.

# Testing the Edge cases

Excellent question! Testing edge cases is crucial for ensuring the robustness of your system. Here are some examples of edge cases we can test for our pizza ordering system, along with the corresponding JUnit tests: [1]

Attempting to create a pizza with an empty or null type:

```java
class PizzaFactoryEdgeCaseTest {
    @Test
    void testCreatePizzaWithEmptyType() {
        PizzaFactory factory = PizzaFactory.getInstance();
        assertThrows(IllegalArgumentException.class, () -> factory.createPizza(""));
    }

    @Test
    void testCreatePizzaWithNullType() {
        PizzaFactory factory = PizzaFactory.getInstance();
        assertThrows(IllegalArgumentException.class, () -> factory.createPizza(null));
    }
}
```

Testing case sensitivity in pizza type:

```java
class PizzaFactoryCaseSensitivityTest {
    @Test
    void testCreatePizzaWithMixedCase() {
        PizzaFactory factory = PizzaFactory.getInstance();
        Pizza pizza = factory.createPizza("VeGGiE");
        assertNotNull(pizza);
        assertTrue(pizza instanceof VeggiePizza);
    }
}
```

Testing concurrent access to singletons:

```java
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SingletonConcurrencyTest {
    @Test
    void testPizzaHandlerConcurrentAccess() throws InterruptedException {
        final int threadCount = 100;
        final CountDownLatch latch = new CountDownLatch(threadCount);
        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                PizzaHandler handler = PizzaHandler.getInstance();
                latch.countDown();
                return handler;
            });
        }

        latch.await(5, TimeUnit.SECONDS);
        executorService.shutdown();

        PizzaHandler referenceHandler = PizzaHandler.getInstance();
        for (int i = 0; i < threadCount; i++) {
            PizzaHandler handler = PizzaHandler.getInstance();
            assertSame(referenceHandler, handler, "All instances should be the same");
        }
    }
}
```

Testing ingredient update functionality:

```java
class IngredientHandlerUpdateTest {
    @Test
    void testUpdateIngredient() {
        SpinachHandler handler = SpinachHandler.getInstance();
        Ingredient originalIngredient = handler.create();

        Ingredient newIngredient = new Spinach() {
            @Override
            public String getName() {
                return "Baby Spinach";
            }
        };

        handler.update(newIngredient);
        Ingredient updatedIngredient = handler.create();

        assertNotEquals(originalIngredient.getName(), updatedIngredient.getName());
        assertEquals("Baby Spinach", updatedIngredient.getName());
    }
}
```

Testing pizza with no ingredients:

```java
class EmptyPizzaTest {
    @Test
    void testCreateEmptyPizza() {
        Pizza emptyPizza = new AbstractPizza() {};
        assertTrue(emptyPizza.getIngredients().isEmpty());
    }
}
```

Testing adding duplicate ingredients:

```java
class DuplicateIngredientTest {
    @Test
    void testAddDuplicateIngredient() {
        Pizza pizza = new VeggiePizza();
        Ingredient spinach = new Spinach();

        pizza.addIngredient(spinach);
        pizza.addIngredient(spinach);

        assertEquals(2, pizza.getIngredients().size());
        assertEquals(2, pizza.getIngredients().stream().filter(i -> i.getName().equals("Spinach")).count());
    }
}
```

Testing pizza creation with maximum allowed ingredients:

```java
class MaxIngredientsTest {
    private static final int MAX_INGREDIENTS = 10; // Assume this is the maximum allowed

    @Test
    void testCreatePizzaWithMaxIngredients() {
        Pizza pizza = new VeggiePizza();
        PizzaBuilder builder = new PizzaBuilder(pizza);

        for (int i = 0; i < MAX_INGREDIENTS; i++) {
            builder.addHandler(new IngredientHandler() {
                public Ingredient create() {
                    return new Ingredient() {
                        public String getName() { return "Ingredient" + i; }
                    };
                }
                public void update(Ingredient ingredient) {}
            });
        }

        Pizza builtPizza = builder.build();
        assertEquals(MAX_INGREDIENTS, builtPizza.getIngredients().size());
    }

    @Test
    void testExceedMaxIngredients() {
        Pizza pizza = new VeggiePizza();
        PizzaBuilder builder = new PizzaBuilder(pizza);

        for (int i = 0; i < MAX_INGREDIENTS + 1; i++) {
            builder.addHandler(new IngredientHandler() {
                public Ingredient create() {
                    return new Ingredient() {
                        public String getName() { return "Ingredient" + i; }
                    };
                }
                public void update(Ingredient ingredient) {}
            });
        }

        assertThrows(IllegalStateException.class, builder::build);
    }
}
```

These edge case tests cover scenarios such as:

* Invalid input handling
* Case sensitivity
* Concurrent access to singletons
* Updating ingredients
* Empty pizzas
* Duplicate ingredients
* Maximum ingredient limits

Remember to adjust your implementation to handle these edge cases appropriately. For example, you might need to add null checks, implement a maximum ingredient limit, or ensure thread-safety in your singleton implementations if you haven't already done so.

By including these edge case tests, you significantly improve the robustness and reliability of your pizza ordering system, helping to catch potential issues before they occur in production.

**Sources**
[1] [Edge Cases in Software Testing. The concept of edge cases emerges as… | by Olha Holota from TestCaseLab | Mar, 2024](https://medium.com/@case_lab/edge-cases-in-software-testing-2a30c2219235)

# Rewriting using Builder Pattern

```java
import lombok.Builder;
import lombok.Singular;
import java.util.List;
import java.util.ArrayList;

// Base Ingredient class
public abstract class Ingredient {
    protected String name;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Concrete Ingredient classes
class Spinach extends Ingredient {
    public Spinach() {
        super("Spinach");
    }
}

class EggPlant extends Ingredient {
    public EggPlant() {
        super("Eggplant");
    }
}

class MozzarellaCheese extends Ingredient {
    public MozzarellaCheese() {
        super("Mozzarella Cheese");
    }
}

class PepperJackCheese extends Ingredient {
    public PepperJackCheese() {
        super("Pepper Jack Cheese");
    }
}

// Base Pizza class
public abstract class Pizza {
    protected List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}

// Concrete Pizza classes
class VeggiePizza extends Pizza {}
class PepperoniPizza extends Pizza {}

// Abstract IngredientHandler
public abstract class IngredientHandler {
    protected Ingredient ingredient;

    public abstract Ingredient create();
    public abstract void update(Ingredient ingredient);
}

// Concrete IngredientHandlers
@Builder
public class SpinachHandler extends IngredientHandler {
    private static SpinachHandler instance;

    private SpinachHandler() {}

    public static synchronized SpinachHandler getInstance() {
        if (instance == null) {
            instance = SpinachHandler.builder().build();
        }
        return instance;
    }

    public Ingredient create() {
        ingredient = new Spinach();
        return ingredient;
    }

    public void update(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}

@Builder
public class EggPlantHandler extends IngredientHandler {
    private static EggPlantHandler instance;

    private EggPlantHandler() {}

    public static synchronized EggPlantHandler getInstance() {
        if (instance == null) {
            instance = EggPlantHandler.builder().build();
        }
        return instance;
    }

    public Ingredient create() {
        ingredient = new EggPlant();
        return ingredient;
    }

    public void update(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}

@Builder
public class MozzarellaCheeseHandler extends IngredientHandler {
    private static MozzarellaCheeseHandler instance;

    private MozzarellaCheeseHandler() {}

    public static synchronized MozzarellaCheeseHandler getInstance() {
        if (instance == null) {
            instance = MozzarellaCheeseHandler.builder().build();
        }
        return instance;
    }

    public Ingredient create() {
        ingredient = new MozzarellaCheese();
        return ingredient;
    }

    public void update(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}

@Builder
public class PepperJackCheeseHandler extends IngredientHandler {
    private static PepperJackCheeseHandler instance;

    private PepperJackCheeseHandler() {}

    public static synchronized PepperJackCheeseHandler getInstance() {
        if (instance == null) {
            instance = PepperJackCheeseHandler.builder().build();
        }
        return instance;
    }

    public Ingredient create() {
        ingredient = new PepperJackCheese();
        return ingredient;
    }

    public void update(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}

@Builder
public class PizzaFactory {
    private static PizzaFactory instance;

    private PizzaFactory() {}

    public static synchronized PizzaFactory getInstance() {
        if (instance == null) {
            instance = PizzaFactory.builder().build();
        }
        return instance;
    }

    public Pizza createPizza(String type) {
        Pizza pizza;
        PizzaBuilder builder;

        switch (type.toLowerCase()) {
            case "veggie":
                pizza = new VeggiePizza();
                builder = PizzaBuilder.builder()
                    .pizza(pizza)
                    .handler(SpinachHandler.getInstance())
                    .handler(EggPlantHandler.getInstance())
                    .handler(PepperJackCheeseHandler.getInstance())
                    .build();
                break;
            case "pepperoni":
                pizza = new PepperoniPizza();
                builder = PizzaBuilder.builder()
                    .pizza(pizza)
                    .handler(SpinachHandler.getInstance())
                    .handler(EggPlantHandler.getInstance())
                    .handler(MozzarellaCheeseHandler.getInstance())
                    .build();
                break;
            default:
                throw new IllegalArgumentException("Unknown pizza type: " + type);
        }

        return builder.build();
    }
}

@Builder
public class PizzaHandler {
    private static PizzaHandler instance;
    private PizzaFactory pizzaFactory;

    private PizzaHandler() {
        this.pizzaFactory = PizzaFactory.getInstance();
    }

    public static synchronized PizzaHandler getInstance() {
        if (instance == null) {
            instance = PizzaHandler.builder()
                .pizzaFactory(PizzaFactory.getInstance())
                .build();
        }
        return instance;
    }

    public Pizza createPizza(String type) {
        return pizzaFactory.createPizza(type);
    }

    public void displayPizzaIngredients(Pizza pizza) {
        System.out.println("Pizza ingredients:");
        for (Ingredient ingredient : pizza.getIngredients()) {
            System.out.println("- " + ingredient.getName());
        }
    }
}

@Builder
public class PizzaBuilder {
    private Pizza pizza;
    @Singular private List<IngredientHandler> handlers;

    public Pizza build() {
        for (IngredientHandler handler : handlers) {
            pizza.addIngredient(handler.create());
        }
        return pizza;
    }
}

public class PizzaOrderSystem {
    public static void main(String[] args) {
        PizzaHandler handler = PizzaHandler.getInstance();

        Pizza veggiePizza = handler.createPizza("veggie");
        System.out.println("Veggie Pizza:");
        handler.displayPizzaIngredients(veggiePizza);

        System.out.println();

        Pizza pepperoniPizza = handler.createPizza("pepperoni");
        System.out.println("Pepperoni Pizza:");
        handler.displayPizzaIngredients(pepperoniPizza);
    }
}
```

This complete code includes:

1. Base Ingredient class and its concrete implementations.
2. Base Pizza class and its concrete implementations.
3. Abstract IngredientHandler class and its concrete implementations using Lombok's @Builder.
4. PizzaFactory class using Lombok's@Builder`.
5. PizzaHandler class using Lombok's @Builder.
6. PizzaBuilder class using Lombok's @Builder and @Singular for the handlers list.
7. PizzaOrderSystem class demonstrating the usage of the pizza creation system.

This code maintains the Singleton pattern for handlers and factories, uses the Builder pattern where appropriate, and demonstrates how to create and display different types of pizzas. Note that you'll need to have Lombok in your project dependencies to use the @Builder and @Singular annotations.

# References

1. [Become a Better Java Developer: Master these 5 Creational Design Patterns by Corey Duffy](https://levelup.gitconnected.com/become-a-better-java-developer-master-these-5-creational-design-patterns-4e91e0100e04)
1. [Using the @Singular Annotation with Lombok Builders](https://www.baeldung.com/lombok-builder-singular)