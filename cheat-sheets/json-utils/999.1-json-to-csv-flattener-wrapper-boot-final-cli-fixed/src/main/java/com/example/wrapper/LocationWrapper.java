package com.example.wrapper;

import com.example.intf.LocationIntf;
import com.example.model.Location;

import java.util.List;

public class LocationWrapper implements LocationIntf {
    private final Location delegate;

    public LocationWrapper(Location delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getType() {
        return delegate.getType();
    }

    @Override
    public String getZip() {
        return delegate.getZip();
    }

    @Override
    public List<String> getCoords() {
        return delegate.getCoords();
    }
}