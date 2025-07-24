package com.example.wrapper;

import com.example.model.Location;

import java.util.List;

public class LocationWrapper {
    private final Location location;

    public LocationWrapper(Location location) {
        this.location = location;
    }

    public String getType() {
        return location.getType();
    }

    public String getZip() {
        return location.getZip();
    }

    public List<String> getCoords() {
        return location.getCoords();
    }
}