package demo.client;

import demo.model.Order;
import demo.model.OrderType;
import demo.model.Vehicle;
import demo.service.VehicleService;

public class CORDemo {
	
    public static void main(String[] args) {
        VehicleService vehicleService = new VehicleService();

        Order toyotaOrder = Order.builder()
                .model("Toyota Camry")
                .color("Blue")
                .orderType(OrderType.TOYOTA)
                .build();

        System.out.println("Creating Toyota Vehicle...");
        Vehicle toyotaVehicle = vehicleService.createVehicle(toyotaOrder);
        System.out.println("Created Toyota Vehicle: " + toyotaVehicle);

        Order teslaOrder = Order.builder()
                .model("Tesla Model S")
                .color("Red")
                .orderType(OrderType.TESLA)
                .build();

        System.out.println("\nCreating Tesla Vehicle...");
        Vehicle teslaVehicle = vehicleService.createVehicle(teslaOrder);
        System.out.println("Created Tesla Vehicle: " + teslaVehicle);

        // Update examples
        toyotaOrder = Order.builder()
                .model("Toyota Camry")
                .color("Green")
                .orderType(OrderType.TOYOTA)
                .build();
        vehicleService.updateVehicle(toyotaVehicle, toyotaOrder);

        teslaOrder = Order.builder()
                .model("Tesla Model S")
                .color("Black")
                .orderType(OrderType.TESLA)
                .build();
        vehicleService.updateVehicle(teslaVehicle, teslaOrder);
    }
}

