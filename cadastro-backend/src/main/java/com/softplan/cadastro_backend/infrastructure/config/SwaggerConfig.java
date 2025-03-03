package com.softplan.cadastro_backend.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do Swagger/OpenAPI para a aplicação.
 * <p>
 * Define as informações básicas da API, como título, versão, descrição e licença.
 * </p>
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configura o OpenAPI com as informações da API.
     *
     * @return Instância configurada do OpenAPI.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cadastro Backend API")
                        .version("1.0")
                        .description("API para gerenciar cadastros de pessoas")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
