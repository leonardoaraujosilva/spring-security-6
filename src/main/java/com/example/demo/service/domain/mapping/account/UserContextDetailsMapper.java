package com.example.demo.service.domain.mapping.account;

import com.example.demo.config.properties.JwtProperties;
import com.example.demo.dto.auth.UserContextDetails;
import com.example.demo.model.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class UserContextDetailsMapper {

    private final ModelMapper modelMapper;
    private final JwtProperties jwtProperties;

    @PostConstruct
    public void configure() {
        createFromUser();
    }

    private void createFromUser() {
        modelMapper.createTypeMap(User.class, UserContextDetails.class).setConverter(mappingContext -> {
            var src = mappingContext.getSource();

            var issuedAt = LocalDateTime.now();
            var userDetails = new UserContextDetails();
            userDetails.setId(src.getId());
            userDetails.setEnabled(src.isEnabled());
            userDetails.setEmail(src.getEmail());
            userDetails.setPassword(src.getPassword());
            userDetails.setIssuedAt(issuedAt);
            userDetails.setExpiresAt(issuedAt.plusSeconds(jwtProperties.getExpirationTime()));
            userDetails.setExpiresIn(jwtProperties.getExpirationTime());
            return userDetails;
        });
    }
}
