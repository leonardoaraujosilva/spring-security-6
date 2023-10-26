package com.example.demo.dto.account.auth;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthenticationResponse {

    private String id;

    private String accessToken;

    private String refreshToken;

    private LocalDateTime issuedAt;

    private LocalDateTime expireAt;

    private Long expiresIn;

}
