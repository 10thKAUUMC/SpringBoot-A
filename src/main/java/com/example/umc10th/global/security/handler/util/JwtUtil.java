package com.example.umc10th.global.security.handler.util; // 패키지 경로 확인!

import com.example.umc10th.global.entity.AuthMember;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Duration accessExpiration;

    public JwtUtil(
            @Value("${jwt.token.secretKey}") String secret,
            @Value("${jwt.token.expiration.access}") Long accessExpiration
    ) {
        //Setting duration and encryption key
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration);
    }

    // Generating Accessing Token (Extract information from AuthMember object)
    public String createAccessToken(AuthMember member) {
        return createToken(member, accessExpiration);
    }

    public String getEmail(String token) {
        try {
            //Through getPayload(), it accesses Claims and extract subject(email)
            return getClaims(token).getPayload().getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // If the token is expired or the format is wrong, returns false
            return false;
        }
    }

    //Generates token creation logic
    private String createToken(AuthMember member, Duration expiration) {
        Instant now = Instant.now();

        // Extract Access role
        String authorities = member.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(member.getUsername()) // Subject stores email
                .claim("role", authorities)
                .claim("email", member.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expiration)))
                .signWith(secretKey)
                .compact();
    }

    //Authorize or parse token
    private Jws<Claims> getClaims(String token) throws JwtException {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
    }
}