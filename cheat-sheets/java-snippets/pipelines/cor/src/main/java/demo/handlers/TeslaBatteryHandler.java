package demo.handlers;

import demo.model.Order;
import demo.model.Vehicle;

public class TeslaBatteryHandler extends BatteryHandler {

	static TeslaBatteryHandler INSTANCE = new TeslaBatteryHandler();
	
	public static TeslaBatteryHandler getInstance() {
		return INSTANCE;
	}
	
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