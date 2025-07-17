package com.example.wrapper;

import com.example.model.Address;
import java.util.List;
import java.util.stream.Collectors;

public class AddressWrapper {
    private final Address address;

    public AddressWrapper(Address address) {
        this.address = address;
    }

    public String getCity() {
        return address.getCity();
    }

    public String getZip() {
        return address.getZip();
    }

    public List<LocationWrapper> getLocations() {
        return address.getLocations().stream()
                .map(LocationWrapper::new)
                .collect(Collectors.toList());
    }
}