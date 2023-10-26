package com.example.demo.dto.account;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateAccountResponse {

    private UUID id;

    private String email;

    private LocalDateTime createdAt;

}
