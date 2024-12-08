package com.practice.project.service.sample;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FullUser implements User {
    private final String username;
    private final String email;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getEmail() {
        return email;
    }
}
