package demo.handlers;

import demo.model.Order;
import demo.model.Vehicle;

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