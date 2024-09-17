package com.example.userregistrationportal.repository;

import com.example.userregistrationportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Custom query to find a user by email
    Optional<User> findByEmail(String email);
    
    // Custom query to check if a user exists by email
    boolean existsByEmail(String email);
}
