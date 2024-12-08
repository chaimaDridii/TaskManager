package com.example.taskmanager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Applique la configuration CORS à toutes les routes API
                .allowedOrigins("http://localhost:4200")  // Autorise les requêtes depuis localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Autorise les méthodes nécessaires
                .allowedHeaders("*");  // Autorise tous les en-têtes
    }
   
}
