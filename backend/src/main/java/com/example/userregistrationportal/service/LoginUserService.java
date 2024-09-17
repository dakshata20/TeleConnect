package com.example.userregistrationportal.service;
 
import org.springframework.stereotype.Service;
 
import com.example.userregistrationportal.entity.LoginUser;
import com.example.userregistrationportal.repository.LoginUserRepository;
 
import java.util.Optional;
 
@Service
public class LoginUserService {
 
    private final LoginUserRepository loginUserRepository;
 
    public LoginUserService(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
    }
 
    /**
     * Authenticate a user by checking the email and password.
     * 
     * @param email the email of the user
     * @param password the password of the user
     * @return true if the email and password match, otherwise false
     */
    public boolean authenticateUser(String email, String password) {
        Optional<LoginUser> userOptional = loginUserRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            LoginUser user = userOptional.get();
            // Direct comparison of plain text password
            return password.equals(user.getPassword());
        }
        return false;
    }
 
    /**
     * Check if the email exists in the repository.
     * 
     * @param email the email to check
     * @return true if the email exists, otherwise false
     */
    public boolean doesEmailExist(String email) {
        return loginUserRepository.findByEmail(email).isPresent();
    }
 
    /**
     * Register a new user.
     * 
     * @param loginUser the user details to register
     * @return the registered user
     */
    public LoginUser registerUser(LoginUser loginUser) {
        // Directly save the plain text password
        return loginUserRepository.save(loginUser);
    }
}