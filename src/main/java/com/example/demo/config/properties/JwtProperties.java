package com.example.demo.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("demo.security.jwt")
public class JwtProperties {

    private String prefix = "Bearer ";

    private String password;

    private Long expirationTime;

}
