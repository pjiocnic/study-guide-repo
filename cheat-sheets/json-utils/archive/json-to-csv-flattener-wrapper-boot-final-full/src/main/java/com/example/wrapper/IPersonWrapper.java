package com.example.wrapper;

import com.example.wrapper.IAddressWrapper;

public interface IPersonWrapper {
    int getId();
    String getName();
    String getDisplayName();
    IAddressWrapper getAddress();
}