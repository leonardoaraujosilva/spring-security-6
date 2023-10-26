package com.example.demo.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserContextDetails implements UserDetails, Serializable {

    private UUID id;

    private String email;

    private String password;

    private boolean enabled;

    private LocalDateTime issuedAt;

    private LocalDateTime expiresAt;

    private Long expiresIn;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return LocalDateTime.now().isBefore(expiresAt);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
