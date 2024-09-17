package com.example.userregistrationportal.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.example.userregistrationportal.entity.LoginUser;
 
import java.util.Optional;
 
@Repository
public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
 
    /**
     * Find a LoginUser by their email.
     * 
     * @param email the email of the user to find
     * @return an Optional containing the found user or empty if no user is found
     */
    Optional<LoginUser> findByEmail(String email);
}