package demo.service;

import demo.handlers.BaseHandler;
import demo.handlers.HandlerChainBuilder;
import demo.model.Order;
import demo.model.Vehicle;

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

