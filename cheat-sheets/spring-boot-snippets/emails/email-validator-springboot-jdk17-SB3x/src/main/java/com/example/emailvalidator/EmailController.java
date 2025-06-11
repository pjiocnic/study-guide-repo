package com.example.emailvalidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailValidationService emailValidationService;

    @PostMapping("/validate-email")
    public String validateEmail(@RequestBody EmailRequest request) {
        boolean valid = emailValidationService.validate(request.getEmail());
        return valid ? "✅ Valid email" : "❌ Invalid email (logged)";
    }
}