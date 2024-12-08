package com.practice.project.service.sample;

public class PartialUserProxy implements User {
    private String username;
    private String email;

    public PartialUserProxy(String username) {
        this.username = username;
        // Optional fields can be left uninitialized
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getEmail() {
        return "Email not available"; // Return default or null value for optional fields
    }
}
