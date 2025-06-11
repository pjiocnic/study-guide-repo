package demo.handlers;

import java.util.List;

import demo.model.AutopilotSystem;
import demo.model.Order;
import demo.model.Tesla;
import demo.model.Vehicle;

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