package demo.model;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
public class Vehicle {
    private String model;
    private String color;
    private Engine bestEngine;
    private Seat bestSeat;
    private Battery battery;
    private Set<Engine> engines;
    private Set<Seat> seats;
    private Set<Battery> batteries;
}

