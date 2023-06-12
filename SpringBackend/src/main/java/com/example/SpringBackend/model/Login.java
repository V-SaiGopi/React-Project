package com.example.SpringBackend.model;

public class Login{
    private String username;
    private String password;

    // Default constructor (required for JSON deserialization)
    public Login() {
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
