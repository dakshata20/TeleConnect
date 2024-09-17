package com.example.userregistrationportal.entity;
 
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "verification_status")
public class LoginVerificationStatus {
 
    @Id
    private Long id;
 
    private String email;
 
    // Getters and setters
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    @Override
    public String toString() {
        return "LoginVerificationStatus{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}