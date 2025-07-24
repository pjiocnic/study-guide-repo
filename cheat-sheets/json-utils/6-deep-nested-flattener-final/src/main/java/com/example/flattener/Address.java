package com.example.flattener;

import java.util.List;

public class Address {
    private String city;
    private String zip;
    private List<Location> locations;

    public String getCity() { return city; }
    public String getZip() { return zip; }
    public List<Location> getLocations() { return locations; }
}
