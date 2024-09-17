package com.example.userregistrationportal.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.userregistrationportal.entity.LoginUser;
import com.example.userregistrationportal.service.LoginUserService;
import com.example.userregistrationportal.service.LoginVerificationStatusService;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private LoginVerificationStatusService loginVerificationStatusService;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        // Debug log
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        if (!loginUserService.doesEmailExist(email)) {
            return ResponseEntity.status(404).body("User not registered, please register");
        }

        boolean isAuthenticated = loginUserService.authenticateUser(email, password);

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody LoginUser loginUser) {
        boolean isEmailRegistered = loginVerificationStatusService.isEmailRegistered(loginUser.getEmail());

        if (isEmailRegistered) {
            return ResponseEntity.status(400).body("Email is already registered");
        }

        LoginUser registeredUser = loginUserService.registerUser(loginUser);
        return ResponseEntity.ok("User registered successfully with ID: " + registeredUser.getId());
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String email) {
        boolean isEmailRegistered = loginVerificationStatusService.isEmailRegistered(email);

        if (isEmailRegistered) {
            return ResponseEntity.ok("Email is registered with us");
        } else {
            return ResponseEntity.status(404).body("The email is not registered with us");
        }
    }
}
