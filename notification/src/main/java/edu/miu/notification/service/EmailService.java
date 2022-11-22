package edu.miu.notification.service;

public interface EmailService {
    void sendEmail (String to, String subject, String message);
    void sendHtmlEmail (String to, String subject, String message);
}
