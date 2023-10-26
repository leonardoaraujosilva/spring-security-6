package com.example.demo.api.account;

import com.example.demo.dto.account.CreateAccountRequest;
import com.example.demo.dto.account.CreateAccountResponse;
import com.example.demo.service.api.account.CreateAccountApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateAccountApiController {

    private final CreateAccountApiService createAccountApiService;

    @PostMapping(value = "/v1/account/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
        var response = createAccountApiService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

}
