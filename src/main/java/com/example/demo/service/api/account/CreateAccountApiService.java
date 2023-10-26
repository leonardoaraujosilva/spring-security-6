package com.example.demo.service.api.account;

import com.example.demo.dto.account.CreateAccountRequest;
import com.example.demo.dto.account.CreateAccountResponse;
import com.example.demo.model.User;
import com.example.demo.service.domain.account.CreateAccountService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccountApiService {

    private final ModelMapper modelMapper;
    private final CreateAccountService createAccountService;

    public CreateAccountResponse createAccount(CreateAccountRequest request) {
        var user = modelMapper.map(request, User.class);
        var stored = createAccountService.createAccount(user);
        return modelMapper.map(stored, CreateAccountResponse.class);
    }
}
