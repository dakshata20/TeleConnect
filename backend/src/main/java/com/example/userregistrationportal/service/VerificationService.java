package com.example.userregistrationportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.userregistrationportal.entity.VerificationStatus;
import com.example.userregistrationportal.repository.VerificationStatusRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import java.util.Random;

@Service
public class VerificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VerificationStatusRepository verificationStatusRepository;

    // Generates a 6-digit OTP
    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Sends a success email with OTP
    private String sendVerificationEmail(String to) {
        String otp = generateOtp();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Document Verification Successful");
        message.setText("Dear User,\r\n"
                + "\r\n"
                + "Your document has been successfully verified.\r\n"
                + "\r\n"
                + "For secure login, please use the following One-Time Password (OTP): " + otp + "\n\n"
                + "Please click the link below to be redirected to the vendor site.\r\n"
                + "select-plans.com\r\n"
                + "You can now select your plans.\r\n");

        try {
            mailSender.send(message);
            System.out.println("OTP sent to user: " + otp);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Indicate failure to send OTP
        }

        return otp; // Return OTP for session storage
    }

    // Verify email and send OTP
    public String verifyEmail(String email, HttpSession session) {
    	
    	System.out.println("Session ID in verifyEmail: " + session.getId());
        // Check if the email already exists in the verification_status table
        if (verificationStatusRepository.existsByEmail(email)) {
            return "Email already verified";
        }

        // Send verification email with OTP
        String otp = sendVerificationEmail(email);
        if (otp == null) {
            return "Failed to send OTP. Please try again.";
        }

        // Store OTP and email in session for later validation
        session.setAttribute("otp", otp);
        session.setAttribute("email", email);

        return "OTP sent to " + email;
    }

    // Validate OTP
    @Transactional
    public String validateOtp(String otp, HttpSession session) {
    	System.out.println("Session ID in validateOtp: " + session.getId());
        String storedOtp = (String) session.getAttribute("otp");
        String email = (String) session.getAttribute("email");
        System.out.println("Stored OTP: " + storedOtp + ", Email: " + email);

        // Validate OTP
        if (storedOtp != null && storedOtp.equals(otp) && email != null) {
            // Remove OTP and email from session
            session.removeAttribute("otp");
            session.removeAttribute("email");

            // Save the email and status (Successful) in the database
            VerificationStatus verificationStatus = new VerificationStatus();
            verificationStatus.setEmail(email);
            verificationStatus.setStatus("Successful");
            verificationStatusRepository.save(verificationStatus);

            return "OTP validation successful!";
        } else if (email == null) {
            return "Error: Email not found in session.";
        } else {
            return "Invalid OTP. Please try again.";
        }
    }
}
