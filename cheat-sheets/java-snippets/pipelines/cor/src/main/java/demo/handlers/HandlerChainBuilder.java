package demo.handlers;

import java.util.LinkedList;
import java.util.List;

import demo.model.OrderType;

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