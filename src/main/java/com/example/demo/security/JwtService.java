package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${app.security.secretkey}")
    private String secret;

    @Value("${app.security.expirationMinutes}")
    private long expirationMinutes;

    private Key signingKey;
    private long expirationMillis;          // tiempo de vida en milisegundos

    @PostConstruct
    void init() {
        signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        expirationMillis = TimeUnit.MINUTES.toMillis(expirationMinutes);
    }

    public String generateToken(UserDetails user) {
        Date issued = new Date();
        Date expiry = new Date(issued.getTime() + expirationMillis);

        CustomUserDetails customUserDetails = (CustomUserDetails) user;

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", customUserDetails.getId())
                .setIssuedAt(issued)
                .setExpiration(expiry)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            parse(token);                       // valida firma y expiración
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public Long extractUserId(String token) {
        return parse(token).getBody().get("userId", Long.class);
    }

    public String extractName(String token) {
        return parse(token).getBody().get("name", String.class);
    }

    public String extractUsername(String token) {
        return parse(token).getBody().getSubject();
    }

    public List<String> extractRoles(String token) {
        return parse(token).getBody().get("roles", List.class);
    }

    public Authentication getAuthentication(String token,
                                            UserDetailsService uds) {
        String username = extractUsername(token);
        UserDetails user = uds.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities());
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token);
    }

    public long getExpirationMillis() {
        return expirationMillis;
    }

}
