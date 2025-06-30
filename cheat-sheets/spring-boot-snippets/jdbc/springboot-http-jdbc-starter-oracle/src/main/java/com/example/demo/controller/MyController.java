
package com.example.demo.controller;

import com.example.demo.service.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController
{
    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/call-external")
    public String callExternalApi() {
        return myService.callExternalApi();
    }

    @GetMapping("/get-users")
    public String getUsers() {
        return myService.getUsers();
    }

    @GetMapping("/update-user")
    public String updateUser() {
        return myService.updateUser();
    }
}
