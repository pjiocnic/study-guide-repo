package com.example.wrapper;

import java.util.List;

public interface ILocationWrapper {
    String getType();
    String getZip();
    List<String> getCoords();
}