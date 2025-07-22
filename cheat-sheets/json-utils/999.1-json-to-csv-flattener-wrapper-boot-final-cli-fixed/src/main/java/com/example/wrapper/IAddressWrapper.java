package com.example.wrapper;

import java.util.List;
import com.example.wrapper.ILocationWrapper;

public interface IAddressWrapper {
    String getCity();
    String getZip();
    List<ILocationWrapper> getLocations();
}