package com.example.smtpsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private RateLimiter rateLimiter;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest request) {
        if (!rateLimiter.tryConsume()) {
            return "429 Too Many Requests - Rate limit exceeded";
        }

        emailService.sendEmail(request.getTo(), request.getSubject(), request.getBody());
        return "✅ Email sent successfully!";
    }
}
