package com.example.demo.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("pedidoson.security")
public class SecurityProperties {

    private JwtProperties jwt;

    private Integer minimalDifferentPassword;

}
