package com.example.userregistrationportal.entity;
 
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "user")
public class LoginUser {
 
    @Id
    private Long id;
 
    @jakarta.validation.constraints.NotEmpty(message = "Aadhar number cannot be empty")
    private String aadharNumber;
 
    @jakarta.validation.constraints.Email(message = "Email should be valid")
    @jakarta.validation.constraints.NotEmpty(message = "Email cannot be empty")
    private String email;
 
    @jakarta.validation.constraints.NotEmpty(message = "First name cannot be empty")
    private String firstName;
 
    @jakarta.validation.constraints.NotEmpty(message = "Last name cannot be empty")
    private String lastName;
 
    @jakarta.validation.constraints.NotEmpty(message = "Password cannot be empty")
    @jakarta.validation.constraints.Size(min = 6, message = "Password should be at least 6 characters long")
    private String password;
 
    @jakarta.validation.constraints.NotEmpty(message = "Phone number cannot be empty")
    private String phoneNumber;
 
    // Getters and Setters
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getAadharNumber() {
        return aadharNumber;
    }
 
    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getPhoneNumber() {
        return phoneNumber;
    }
 
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
 
    @Override
    public String toString() {
        return "LoginUser{" +
                "id=" + id +
                ", aadharNumber='" + aadharNumber + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}