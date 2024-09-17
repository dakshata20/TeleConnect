package com.example.userregistrationportal.model;

@jakarta.persistence.Entity
public class User {

    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @jakarta.persistence.Column(nullable = false)
    private String firstName;

    @jakarta.persistence.Column(nullable = false)
    private String lastName;

    @jakarta.persistence.Column(nullable = false, unique = true)
    private String email;

    @jakarta.persistence.Column(nullable = false)
    private String phoneNumber;

    @jakarta.persistence.Column(nullable = false, unique = true)
    private String aadharNumber;

    @jakarta.persistence.Column(nullable = false)
    private String password;

    // Default constructor
    public User() {
    }

    // Constructor with parameters
    public User(String firstName, String lastName, String email, String phoneNumber, String aadharNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.aadharNumber = aadharNumber;
        this.password = password;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
