package demo.handlers;

import java.util.List;

import demo.model.Order;
import demo.model.Toyota;
import demo.model.Vehicle;

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