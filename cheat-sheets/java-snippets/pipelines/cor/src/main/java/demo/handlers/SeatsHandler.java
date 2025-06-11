package demo.handlers;

import demo.model.Order;
import demo.model.Vehicle;

public class SeatsHandler extends ComponentHandler {

	static SeatsHandler INSTANCE = new SeatsHandler();
	
	public static SeatsHandler getInstance() {
		return INSTANCE;
	}
	
	@Override
	public boolean create(Vehicle vehicle, Order order) {
		// Your logic to create engine for the vehicle
		boolean didCreate = true;
		// possibly add engines to the vehicle
		return didCreate;
	}

	@Override
	protected void markBestInd(Vehicle vehicle) {
		// Mark the best engine indicator or perform other post-processing
	}

	@Override
	public boolean update(Vehicle vehicle, Order order) {
		// Your logic to update engine for the vehicle
		boolean didUpdate = true;
		return didUpdate;
	}

}
