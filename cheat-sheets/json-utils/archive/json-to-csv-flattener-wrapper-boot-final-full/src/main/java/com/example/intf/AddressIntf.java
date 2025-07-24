package com.example.intf;

import java.util.List;

public interface AddressIntf {
    String getCity();
    String getZip();
    List<LocationIntf> getLocations();
}