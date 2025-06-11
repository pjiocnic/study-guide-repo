package com.example.emailbatch;

public class EmailRecord {
    private Long id;
    private String emailAddress;
    private String subject;
    private String body;

    public EmailRecord(Long id, String emailAddress, String subject, String body) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.subject = subject;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
