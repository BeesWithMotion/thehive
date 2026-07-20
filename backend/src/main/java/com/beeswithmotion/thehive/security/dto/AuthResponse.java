package com.beeswithmotion.thehive.security.dto;

public class AuthResponse {
    private final String token;
    private final String username;

    public AuthResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }
}
