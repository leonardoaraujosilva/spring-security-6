package com.example.demo.api.account.auth;

import com.example.demo.dto.account.auth.AuthenticationRequest;
import com.example.demo.dto.account.auth.AuthenticationResponse;
import com.example.demo.service.api.account.auth.AuthenticationApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationApiService authenticationApiService;

    @PostMapping(value = "/v1/oauth/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> getToken(@RequestBody AuthenticationRequest request) {
        var response = authenticationApiService.authenticate(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
