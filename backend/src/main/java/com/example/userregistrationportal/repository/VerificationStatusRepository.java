package com.example.userregistrationportal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.userregistrationportal.entity.VerificationStatus;

@Repository
public interface VerificationStatusRepository extends JpaRepository<VerificationStatus, Long> {

    // Method to find a VerificationStatus by email
    VerificationStatus findByEmail(String email);
	

    // Optionally, you might want to add a method to check if a verification status already exists
    boolean existsByEmail(String email);
}
