package com.example.taskmanager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Désactiver CSRF pour simplifier le développement
            .authorizeHttpRequests()
                .anyRequest().permitAll(); // Autoriser toutes les requêtes sans authentification
        return http.build();
    }
}
