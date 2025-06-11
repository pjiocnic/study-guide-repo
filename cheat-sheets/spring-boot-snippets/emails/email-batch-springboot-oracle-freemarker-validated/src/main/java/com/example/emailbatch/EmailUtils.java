package com.example.emailbatch;

import org.apache.commons.validator.routines.EmailValidator;

public class EmailUtils {
    public static boolean isValid(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
