package com.example.userregistrationportal.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    /**
     * Sends a simple email message.
     *
     * @param to      the recipient's email address
     * @param subject the subject of the email
     * @param text    the body of the email
     */
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    /**
     * Sends an email with OTP for verification.
     *
     * @param to   the recipient's email address
     * @param otp  the OTP to be sent
     */
    public void sendOtpEmail(String to, String otp) {
        String subject = "Your OTP Code";
        String text = String.format("Your OTP code is %s. Please use this code to complete your verification process.", otp);
        sendSimpleMessage(to, subject, text);
    }

    /**
     * Sends an email for verification failure.
     *
     * @param to     the recipient's email address
     * @param reason the reason for failure
     */
    public void sendFailureEmail(String to, String reason) {
        String subject = "Verification Failed";
        String text = String.format("Your verification process has failed. Reason: %s", reason);
        sendSimpleMessage(to, subject, text);
    }
}