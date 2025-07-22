package com.example.wrapper;

import com.example.intf.AddressIntf;
import com.example.intf.LocationIntf;
import com.example.model.Address;

import java.util.List;
import java.util.stream.Collectors;

public class AddressWrapper implements AddressIntf {
    private final Address delegate;

    public AddressWrapper(Address delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getCity() {
        return delegate.getCity();
    }

    @Override
    public String getZip() {
        return delegate.getZip();
    }

    @Override
    public List<LocationIntf> getLocations() {
        return delegate.getLocations().stream()
            .map(LocationWrapper::new)
            .collect(Collectors.toList());
    }
}