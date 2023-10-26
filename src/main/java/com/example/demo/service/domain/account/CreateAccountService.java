package com.example.demo.service.domain.account;

import com.example.demo.config.exception.UserAlreadyExistsException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateAccountService {

    private final UserRepository userRepository;

    @Transactional
    public User createAccount(User user) {
        var found = userRepository.findByEmailAndEnabled(user.getEmail());

        if (found.isPresent())
            throw new UserAlreadyExistsException();

        return userRepository.save(user);
    }
}
