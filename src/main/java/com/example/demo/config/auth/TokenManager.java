package com.example.demo.config.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.config.exception.InvalidTokenException;
import com.example.demo.config.properties.JwtProperties;
import com.example.demo.dto.auth.UserContextDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokenManager {

    private final JwtProperties jwtProperties;

    public UserContextDetails decode(String bearerToken) {
        if (bearerToken != null) {
            if (bearerToken.startsWith(jwtProperties.getPrefix())) {
                var token = bearerToken.replace(jwtProperties.getPrefix(), "");

                var decoded = JWT.require(Algorithm.HMAC256(jwtProperties.getPassword()))
                        .build()
                        .verify(token);

                var userDetails = new UserContextDetails();
                userDetails.setId(UUID.fromString(decoded.getClaim("id").asString()));
                userDetails.setEnabled(true);
                userDetails.setEmail(decoded.getSubject());
                userDetails.setIssuedAt(toLocalDateTime(decoded.getIssuedAt()));
                userDetails.setExpiresAt(toLocalDateTime(decoded.getExpiresAt()));
                return userDetails;
            }
            throw new InvalidTokenException();
        }
        return null;
    }

    public String generate(UserContextDetails userContextDetails) {
        return JWT.create()
                .withClaim("id", userContextDetails.getId().toString())
                .withSubject(userContextDetails.getEmail())
                .withClaim("username", userContextDetails.getUsername())
                .withIssuedAt(userContextDetails.getIssuedAt().toInstant(ZoneOffset.UTC))
                .withExpiresAt(userContextDetails.getExpiresAt().toInstant(ZoneOffset.UTC))
                .sign(Algorithm.HMAC256(jwtProperties.getPassword()));
    }

    private LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
