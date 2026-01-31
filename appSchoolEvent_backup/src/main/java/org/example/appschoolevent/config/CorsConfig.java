package org.example.appschoolevent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // todas las rutas
                        .allowedOrigins(
                                "https://frontend-049h.onrender.com", // frontend deployado
                                "http://localhost:4200",             // frontend local para desarrollo
                                "capacitor://localhost"              // para apps móviles con Capacitor
                        )
                        .allowedMethods("*")   // todos los métodos HTTP
                        .allowCredentials(false); // false si no usas cookies
            }
        };
    }
}
