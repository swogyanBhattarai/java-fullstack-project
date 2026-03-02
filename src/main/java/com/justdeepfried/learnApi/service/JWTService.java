package com.justdeepfried.learnApi.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
public class JWTService {
    Map<String, Object> claims = new HashMap<>();

    byte[] secretKey = "supersecretkeyhatisusedforpasswordencryption".getBytes();

    public String generateToken(String username) {

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 1000 * 30))
                .and()
                .signWith(getKey())
                .compact();
    }

    public Key getKey() {
        return Keys.hmacShaKeyFor(secretKey);
    }
}
