package com.example.model;

import java.util.*;
public class Address {
    public String city;
    public String zip;
    public List<Location> locations;

    public String getCity() { return city; }
    public String getZip() { return zip; }
    public List<Location> getLocations() { return locations; }
}