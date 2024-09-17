package com.example.userregistrationportal.service;

import com.example.userregistrationportal.model.User;
import com.example.userregistrationportal.repository.UserRepository;
import com.example.userregistrationportal.repository.VerificationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationStatusRepository verificationStatusRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean emailExists(String email) {
        // Check if email exists in the user table
        return userRepository.existsByEmail(email);
    }

    public boolean emailExistsInVerificationStatus(String email) {
        // Check if email exists in the verification_status table
        return verificationStatusRepository.existsByEmail(email);
    }
}
