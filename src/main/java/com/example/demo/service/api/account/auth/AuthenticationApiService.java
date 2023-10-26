package com.example.demo.service.api.account.auth;

import com.example.demo.config.auth.TokenManager;
import com.example.demo.dto.account.auth.AuthenticationRequest;
import com.example.demo.dto.account.auth.AuthenticationResponse;
import com.example.demo.dto.auth.UserContextDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationApiService {

    private final TokenManager tokenManager;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        var accountCredentials = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        var authentication = authenticationManager.authenticate(accountCredentials);
        var userContextDetails = (UserContextDetails) authentication.getPrincipal();

        return createResponse(userContextDetails);
    }

    private AuthenticationResponse createResponse(UserContextDetails userContextDetails) {
        var token = tokenManager.generate(userContextDetails);
        var response = new AuthenticationResponse();
        response.setAccessToken(token);
        response.setIssuedAt(userContextDetails.getIssuedAt());
        response.setExpireAt(userContextDetails.getExpiresAt());
        response.setExpiresIn(userContextDetails.getExpiresIn());
        response.setId(userContextDetails.getId().toString());
        return response;

    }
}
