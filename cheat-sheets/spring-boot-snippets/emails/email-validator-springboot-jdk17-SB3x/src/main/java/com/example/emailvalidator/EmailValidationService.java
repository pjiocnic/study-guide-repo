package com.example.emailvalidator;

import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Service
public class EmailValidationService {

    private final String LOG_FILE = "invalid-emails.log";

    public boolean validate(String email) {
        boolean valid = EmailValidatorUtil.isEmailValid(email);

        if (!valid) {
            logInvalidEmail(email);
        }
        return valid;
    }

    private void logInvalidEmail(String email) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.printf("[%s] Invalid email: %s%n", LocalDateTime.now(), email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

