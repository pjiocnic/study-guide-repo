package demo.handlers;

import demo.model.Order;
import demo.model.Vehicle;

public class EngineHandler extends ComponentHandler {

	static EngineHandler INSTANCE = new EngineHandler();
	
	public static EngineHandler getInstance() {
		return INSTANCE;
	}
	
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