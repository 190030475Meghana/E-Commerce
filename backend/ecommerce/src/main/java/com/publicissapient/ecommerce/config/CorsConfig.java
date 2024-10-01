package com.publicissapient.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Adjust the mapping as per your API paths
                .allowedOrigins("http://localhost:3001") // Allow requests from frontend origin
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
