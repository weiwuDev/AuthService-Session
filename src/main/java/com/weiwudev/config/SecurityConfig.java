package com.weiwudev.config;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
@RefreshScope
public class SecurityConfig {

    private final ServerSecurityContextRepository securityContextRepository = new WebSessionServerSecurityContextRepository();

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        return http.securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers("/register").permitAll()
                .pathMatchers("/login", "/check", "/logout").hasAuthority("ROLE_USER")
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .build();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
       // return new CustomPasswordEncoder();
        return new BCryptPasswordEncoder(10);
    }
}
