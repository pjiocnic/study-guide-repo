package com.example.flattener;

import java.util.List;

public class Contact {
    private String phone;
    private String email;
    private List<Social> social;

    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public List<Social> getSocial() { return social; }
}
