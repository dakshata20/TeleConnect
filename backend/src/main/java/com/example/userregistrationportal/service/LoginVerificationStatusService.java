package com.example.userregistrationportal.service;
 
import org.springframework.stereotype.Service;
 
import com.example.userregistrationportal.entity.LoginVerificationStatus;
import com.example.userregistrationportal.repository.LoginVerificationStatusRepository;
 
import java.util.Optional;
 
@Service
public class LoginVerificationStatusService {
 
    private final LoginVerificationStatusRepository loginVerificationStatusRepository;
 
    public LoginVerificationStatusService(LoginVerificationStatusRepository loginVerificationStatusRepository) {
        this.loginVerificationStatusRepository = loginVerificationStatusRepository;
    }
 
    /**
     * Check if the email is registered in the verification status table.
     *
     * @param email the email to check
     * @return true if the email is registered, otherwise false
     */
    public boolean isEmailRegistered(String email) {
        Optional<LoginVerificationStatus> statusOptional = loginVerificationStatusRepository.findByEmail(email);
        return statusOptional.isPresent();
    }
}

 
 