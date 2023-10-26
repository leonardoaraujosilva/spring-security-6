package com.example.demo.dto.account;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateAccountRequest {

    private String email;

    private String password;

}
