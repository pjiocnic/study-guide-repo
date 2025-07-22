package com.example.wrapper;

import com.example.intf.PersonIntf;
import com.example.intf.AddressIntf;
import com.example.model.Person;

public class PersonWrapper implements PersonIntf {
    private final Person delegate;

    public PersonWrapper(Person delegate) {
        this.delegate = delegate;
    }

    @Override
    public int getId() {
        return delegate.getId();
    }

    @Override
    public String getName() {
        return "[User] " + delegate.getName(); // Custom logic
    }

    @Override
    public AddressIntf getAddress() {
        return new AddressWrapper(delegate.getAddress());
    }
}