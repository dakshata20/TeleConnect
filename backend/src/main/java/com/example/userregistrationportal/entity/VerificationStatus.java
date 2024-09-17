package com.example.userregistrationportal.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VerificationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String status;

    // Default constructor
    public VerificationStatus() {
    }

    // Parameterized constructor
    public VerificationStatus(String email, String status) {
        this.email = email;
        this.status = status;
    }

    // Getters and Setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "VerificationStatus{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}