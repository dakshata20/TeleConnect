package com.example.userregistrationportal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.userregistrationportal.service.VerificationService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/verification")
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    @PostMapping("/verify-email")
    public String verifyEmail(@RequestBody Map<String, String> payload, HttpSession session) {
        String email = payload.get("email");
        return verificationService.verifyEmail(email, session);
    }

    @PostMapping("/validate-otp")
    public String validateOtp(@RequestBody Map<String, String> payload, HttpSession session) {
        String otp = payload.get("otp");
        return verificationService.validateOtp(otp, session);
    }
}
