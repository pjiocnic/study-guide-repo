package demo.model;

import java.util.Set;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class Order {
    private OrderType orderType;  // Specifies the type of vehicle (e.g., TOYOTA, TESLA)
    private String model;          // Model name of the vehicle
    private String color;          // Color of the vehicle
    private Set<Engine> engines;   // Set of engines included in the order
    private Set<Seat> seats;       // Set of seats included in the order
    private Set<Battery> batteries; // Set of batteries included in the order
}
