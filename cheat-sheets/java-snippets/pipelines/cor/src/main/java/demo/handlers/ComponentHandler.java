package demo.handlers;

import demo.model.Order;
import demo.model.Vehicle;

public abstract class ComponentHandler {

    public abstract boolean create(Vehicle vehicle, Order order);

    protected abstract void markBestInd(Vehicle vehicle);

    public abstract boolean update(Vehicle vehicle, Order order);
}