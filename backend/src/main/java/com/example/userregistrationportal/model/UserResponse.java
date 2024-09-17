
package com.example.userregistrationportal.model;

public class UserResponse {

    private String status;
    private String message;

    // Default constructor
    public UserResponse() {
    }

    // Constructor with parameters
    public UserResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}