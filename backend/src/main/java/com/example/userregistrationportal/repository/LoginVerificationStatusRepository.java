 
package com.example.userregistrationportal.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.example.userregistrationportal.entity.LoginVerificationStatus;
 
import java.util.Optional;
 
@Repository
public interface LoginVerificationStatusRepository extends JpaRepository<LoginVerificationStatus, Long> {
 
    /**
     * Find a LoginVerificationStatus by their email.
     *
     * @param email the email of the verification status to find
     * @return an Optional containing the found status or empty if no status is found
     */
    Optional<LoginVerificationStatus> findByEmail(String email);
}

 