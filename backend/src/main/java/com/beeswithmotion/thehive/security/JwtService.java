package com.beeswithmotion.thehive.security;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET = "its-a-secret-that-will-be-changed";
    private static final long EXPIRATION_MILLIS = 1000L * 60 * 60 * 24;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(EXPIRATION_MILLIS)))
                .signWith(getSigningKey())
                .compact();
    }
}
