package com.softplan.cadastro_backend.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração de CORS para a aplicação.
 * <p>
 * Permite requisições de origens especificadas, com os métodos HTTP definidos.
 * </p>
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configura os mapeamentos de CORS para a aplicação.
     *
     * @param registry Objeto CorsRegistry para configuração.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // Domínio permitido
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
