package com.example.demo.service.domain.mapping.user;

import com.example.demo.dto.account.CreateAccountRequest;
import com.example.demo.model.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void configure() {
        createFromCreateAccountRequest();
    }

    private void createFromCreateAccountRequest() {
        modelMapper.createTypeMap(CreateAccountRequest.class, User.class).setConverter(mappingContext -> {
            var src = mappingContext.getSource();
            var response = new User();
            response.setEmail(src.getEmail());
            response.setPassword(passwordEncoder.encode(src.getPassword()));
            response.setEnabled(true);
            return response;
        });
    }
}
