<h1>Demo: COR and TemplateMethod</h1>

This design combines aspects of several design patterns, including the **Chain of Responsibility**, **Builder**, and **Template Method** patterns. Here’s how each applies:

### 1. **Chain of Responsibility Pattern**

The `HandlerChainBuilder` constructs a chain of component handlers (like `EngineHandler`, `SeatsHandler`, and `BatteryHandler`). This chain allows requests (vehicle creation and updates) to be passed through a sequence of handlers, each responsible for a specific aspect of the vehicle. Each handler in the chain can handle part of the request and pass it along to the next, enabling extensibility and separation of concerns.

The **Chain of Responsibility** pattern is particularly useful when there’s a need to process a request in a sequence of steps, where each step might modify the request or add new elements. In this design, each sub-handler can add or modify components of a `Vehicle` during creation or update.

### 2. **Builder Pattern**

The `HandlerChainBuilder` uses the **Builder Pattern** to assemble the chain of handlers based on the `OrderType`. This pattern is used to construct complex objects step-by-step in a flexible manner, which in this case allows different configurations of handlers for `Toyota` and `Tesla` vehicles. This setup supports extensibility, as new configurations of handlers can be created by adjusting the handler order or adding new ones.

### 3. **Template Method Pattern**

In the `BaseHandler` class, we see the **Template Method Pattern**. The `create` and `update` methods define a general sequence for creating and updating a `Vehicle` (including `postCreate` and `postUpdate` steps), but they leave the specifics (like `markBestInd`) to be defined by subclasses. Each top-level handler (`ToyotaHandler`, `TeslaHandler`) and component handler (`BatteryHandler`, etc.) provides the specific logic, allowing reuse of the general structure while enabling specialized behavior.

### Summary

This design is often seen in software where:
- **Responsibility needs to be divided across multiple processing steps**.
- **Specialized handling or variations in the sequence of steps are required**.
- **Configuration and modular assembly of handlers (using a builder) need to be controlled based on context**, such as the `OrderType`.

This blend of patterns results in a flexible, maintainable architecture that can easily accommodate new vehicle types, components, or behavior changes, making it highly extensible and loosely coupled.

# The Design

Absolutely, separating the base handlers (for Toyota and Tesla) from the specific component handlers (like `EngineHandler`, `SeatsHandler`, `BatteryHandler`) will improve modularity and readability. Additionally, having `ToyotaBatteryHandler` and `TeslaBatteryHandler` extend `BatteryHandler` aligns with the design for specific battery handling.

Here's how the updated structure would look with these changes:

### Updated Classes

#### 1. **BaseHandler** (Top-Level Handler Base Class)

The `BaseHandler` class serves as the top-level handler interface for Toyota and Tesla handlers. It has `create` and `update` methods that operate on `Order` for `create` and `Vehicle` and `Order` for `update`.

```java
import lombok.Getter;

public abstract class BaseHandler {

    // Create method that initializes a Vehicle based on the Order
    public abstract Vehicle create(Order order);

    // Update method for modifying an existing Vehicle based on an Order
    public abstract boolean update(Vehicle vehicle, Order order);

    // Method to run any post-create actions
    protected void postCreate(Vehicle vehicle) {
        markBestInd(vehicle);
    }

    // Method to run any post-update actions
    protected void postUpdate(Vehicle vehicle) {
        markBestInd(vehicle);
    }

    // Marks the "best" indicators for the Vehicle based on the handler type
    protected abstract void markBestInd(Vehicle vehicle);
}
```

#### 2. **ToyotaHandler** and **TeslaHandler** (Top-Level Handlers)

The `ToyotaHandler` and `TeslaHandler` classes implement the top-level vehicle creation and update logic and delegate to subhandlers for specific components.

```java
import lombok.experimental.Singleton;
import java.util.List;

@Singleton
public class ToyotaHandler extends BaseHandler {

    private final List<ComponentHandler> subHandlers;

    public ToyotaHandler(List<ComponentHandler> subHandlers) {
        this.subHandlers = subHandlers;
    }

    @Override
    public Vehicle create(Order order) {
        Vehicle vehicle = Toyota.builder()
                .model(order.getModel())
                .color(order.getColor())
                .build();

        for (ComponentHandler handler : subHandlers) {
            handler.create(vehicle, order);
        }
        postCreate(vehicle);
        return vehicle;
    }

    @Override
    public boolean update(Vehicle vehicle, Order order) {
        for (ComponentHandler handler : subHandlers) {
            handler.update(vehicle, order);
        }
        postUpdate(vehicle);
        return true;
    }

    @Override
    protected void markBestInd(Vehicle vehicle) {
        // Toyota-specific best indicator logic
    }
}

@Singleton
public class TeslaHandler extends BaseHandler {

    private final List<ComponentHandler> subHandlers;

    public TeslaHandler(List<ComponentHandler> subHandlers) {
        this.subHandlers = subHandlers;
    }

    @Override
    public Vehicle create(Order order) {
        Vehicle vehicle = Tesla.builder()
                .model(order.getModel())
                .color(order.getColor())
                .software(new AutopilotSystem())
                .build();

        for (ComponentHandler handler : subHandlers) {
            handler.create(vehicle, order);
        }
        postCreate(vehicle);
        return vehicle;
    }

    @Override
    public boolean update(Vehicle vehicle, Order order) {
        for (ComponentHandler handler : subHandlers) {
            handler.update(vehicle, order);
        }
        postUpdate(vehicle);
        return true;
    }

    @Override
    protected void markBestInd(Vehicle vehicle) {
        // Tesla-specific best indicator logic
    }
}
```

#### 3. **ComponentHandler** (Interface for Subhandlers)

The `ComponentHandler` interface is the base for handlers managing individual components (like Engine, Seat, and Battery).

```java
public abstract class ComponentHandler {

    public abstract boolean create(Vehicle vehicle, Order order);

    protected abstract void markBestInd(Vehicle vehicle);

    public abstract boolean update(Vehicle vehicle, Order order);
}
```

#### 4. **EngineHandler**, **SeatsHandler**, **BatteryHandler**, **ToyotaBatteryHandler**, and **TeslaBatteryHandler**

Each component handler has logic to handle a specific part of the vehicle. `ToyotaBatteryHandler` and `TeslaBatteryHandler` extend `BatteryHandler` to add custom behavior.

```java
import lombok.experimental.Singleton;

@Singleton
public class EngineHandler extends ComponentHandler {

    @Override
    public boolean create(Vehicle vehicle, Order order) {
        // Engine creation logic
        return true;
    }

    @Override
    protected void markBestInd(Vehicle vehicle) {
        // Engine-specific best indicator logic
    }

    @Override
    public boolean update(Vehicle vehicle, Order order) {
        // Engine update logic
        return true;
    }
}

// Similar setup for SeatsHandler

@Singleton
public class BatteryHandler extends ComponentHandler {

    @Override
    public boolean create(Vehicle vehicle, Order order) {
        // Battery creation logic
        return true;
    }

    @Override
    protected void markBestInd(Vehicle vehicle) {
        // Battery-specific best indicator logic
    }

    @Override
    public boolean update(Vehicle vehicle, Order order) {
        // Battery update logic
        return true;
    }
}

@Singleton
public class ToyotaBatteryHandler extends BatteryHandler {

    @Override
    public boolean create(Vehicle vehicle, Order order) {
        // Toyota-specific battery creation logic
        return true;
    }

    @Override
    protected void markBestInd(Vehicle vehicle) {
        // Toyota-specific best indicator logic for battery
    }

    @Override
    public boolean update(Vehicle vehicle, Order order) {
        // Toyota-specific battery update logic
        return true;
    }
}

@Singleton
public class TeslaBatteryHandler extends BatteryHandler {

    @Override
    public boolean create(Vehicle vehicle, Order order) {
        // Tesla-specific battery creation logic
        return true;
    }

    @Override
    protected void markBestInd(Vehicle vehicle) {
        // Tesla-specific best indicator logic for battery
    }

    @Override
    public boolean update(Vehicle vehicle, Order order) {
        // Tesla-specific battery update logic
        return true;
    }
}
```

#### 5. **HandlerChainBuilder** (To Set Up the Chain of Handlers)

This class constructs the chain of subhandlers based on the order type and attaches them to the correct top-level handler.

```java
import java.util.LinkedList;
import java.util.List;

public class HandlerChainBuilder {

    public BaseHandler buildHandlerChain(OrderType orderType) {
        List<ComponentHandler> subHandlers = new LinkedList<>();

        // Add subHandlers in the desired order
        subHandlers.add(EngineHandler.getInstance());
        subHandlers.add(SeatsHandler.getInstance());

        switch (orderType) {
            case TOYOTA:
                subHandlers.add(ToyotaBatteryHandler.getInstance());
                return new ToyotaHandler(subHandlers);
            case TESLA:
                subHandlers.add(TeslaBatteryHandler.getInstance());
                return new TeslaHandler(subHandlers);
            default:
                throw new IllegalArgumentException("Unknown OrderType: " + orderType);
        }
    }
}
```

#### 6. **VehicleService** (Using the Builder to Create or Update Vehicles)

```java
public class VehicleService {

    private final HandlerChainBuilder handlerChainBuilder;

    public VehicleService() {
        this.handlerChainBuilder = new HandlerChainBuilder();
    }

    public Vehicle createVehicle(Order order) {
        BaseHandler topLevelHandler = handlerChainBuilder.buildHandlerChain(order.getOrderType());
        return topLevelHandler.create(order);
    }

    public void updateVehicle(Vehicle vehicle, Order order) {
        BaseHandler topLevelHandler = handlerChainBuilder.buildHandlerChain(order.getOrderType());
        topLevelHandler.update(vehicle, order);
    }
}
```

#### 7. **Main Class (Client)**

```java
public class Main {

    public static void main(String[] args) {
        VehicleService vehicleService = new VehicleService();

        Order toyotaOrder = Order.builder()
                .model("Toyota Camry")
                .color("Blue")
                .orderType(OrderType.TOYOTA)
                .build();

        System.out.println("Creating Toyota Vehicle...");
        Vehicle toyotaVehicle = vehicleService.createVehicle(toyotaOrder);
        System.out.println("Created Toyota Vehicle: " + toyotaVehicle);

        Order teslaOrder = Order.builder()
                .model("Tesla Model S")
                .color("Red")
                .orderType(OrderType.TESLA)
                .build();

        System.out.println("\nCreating Tesla Vehicle...");
        Vehicle teslaVehicle = vehicleService.createVehicle(teslaOrder);
        System.out.println("Created Tesla Vehicle: " + teslaVehicle);

        // Update examples
        toyotaOrder = Order.builder()
                .model("Toyota Camry")
                .color("Green")
                .orderType(OrderType.TOYOTA)
                .build();
        vehicleService.updateVehicle(toyotaVehicle, toyotaOrder);

        teslaOrder = Order.builder()
                .model("Tesla Model S")
                .color("Black")
                .orderType(OrderType.TESLA)
                .build();
        vehicleService.updateVehicle(teslaVehicle, teslaOrder);
    }
}
```

This structure provides modularity and clear separation, where top-level handlers (`ToyotaHandler`, `TeslaHandler`) are responsible for overall vehicle creation and update, while component handlers (like `EngineHandler`, `SeatsHandler`, `BatteryHandler`) handle individual parts.