package demo.handlers;

import demo.model.Order;
import demo.model.Vehicle;

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