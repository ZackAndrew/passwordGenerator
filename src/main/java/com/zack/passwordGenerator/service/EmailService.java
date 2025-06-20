package com.zack.passwordGenerator.service;

import com.zack.passwordGenerator.model.Users;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void greetingEmail(Users user) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(user.getEmail());
        helper.setSubject("Welcome to our service");

        String htmlContent = "<!DOCTYPE html>" +
                "<html><body style='font-family: Arial, sans-serif;'>" +
                "<div style='padding: 20px; background-color: #f2f2f2; border-radius: 10px;'>" +
                "<h2 style='color: #333;'>Welcome, <span style='color:#007BFF;'>" + user.getUsername() + "</span>!</h2>" +
                "<p>Thank you for registering at <strong>Our Service</strong> 🎉</p>" +
                "<p>You can now:</p>" +
                "<ul>" +
                "<li>Securely store your data</li>" +
                "<li>Use your personal dashboard</li>" +
                "<li>Stay protected with advanced encryption</li>" +
                "</ul>" +
                "<p style='margin-top: 30px;'>Best regards,<br/>The Team</p>" +
                "</div></body></html>";

        helper.setText(htmlContent, true);
        mailSender.send(message);
    }
}
