package com.example.wrapper;

import com.example.model.Person;

public class PersonWrapper {
    private final Person person;

    public PersonWrapper(Person person) {
        this.person = person;
    }

    public String getName() {
        return person.getName();
    }

    public String getDisplayName() {
        return "[User] " + person.getName();
    }

    public AddressWrapper getAddress() {
        return new AddressWrapper(person.getAddress());
    }

    public int getId() {
        return person.getId();
    }
}