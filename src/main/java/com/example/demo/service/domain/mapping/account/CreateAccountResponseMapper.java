package com.example.demo.service.domain.mapping.account;

import com.example.demo.dto.account.CreateAccountResponse;
import com.example.demo.model.User;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CreateAccountResponseMapper {

    private final ModelMapper modelMapper;

    @PostConstruct
    public void configure() {
        createFromUser();
    }

    private void createFromUser() {
        modelMapper.createTypeMap(User.class, CreateAccountResponse.class).setConverter(mappingContext -> {
            var src = mappingContext.getSource();

            var response = new CreateAccountResponse();
            response.setId(src.getId());
            response.setEmail(src.getEmail());
            response.setCreatedAt(src.getCreatedAt());
            return response;
        });
    }

}
