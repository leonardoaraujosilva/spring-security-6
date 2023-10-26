package com.example.demo.config.auth;

import com.example.demo.dto.auth.UserContextDetails;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserContextDetailsService implements UserDetailsService {

    private static final String NOT_FOUND_MSG = "User %s not found";

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmailAndEnabled(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(NOT_FOUND_MSG, username)));

        return modelMapper.map(user, UserContextDetails.class);
    }
}
