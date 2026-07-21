package com.beeswithmotion.thehive.user;

import jakarta.persistence.*;

import java.time.Instant;import java.time.LocalDateTime;

// TODO create User DTOs
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // Don't like explicitly naming it
    private Long id;

    private String username;
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;
    private String role = "user";

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}