package com.kp.services;

import com.kp.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    public String createToken(User user) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes()); // NEW: proper key generation

        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("role",user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256) // UPDATED signing method
                .compact();
    }
}
