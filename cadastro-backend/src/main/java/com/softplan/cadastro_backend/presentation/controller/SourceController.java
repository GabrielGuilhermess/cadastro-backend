package com.softplan.cadastro_backend.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller que fornece os links para os repositórios do projeto.
 * <p>
 * Esse endpoint retorna uma página HTML contendo links para os repositórios do backend e do frontend no GitHub.
 * </p>
 */
@RestController
@RequestMapping("/source")
@Tag(name = "Source", description = "Fornece os links para os repositórios do projeto no GitHub")
public class SourceController {

    /**
     * Retorna uma página HTML com os links para os repositórios do projeto no GitHub.
     *
     * @return Página HTML contendo os links para os repositórios.
     */
    @Operation(summary = "Obter links do projeto", description = "Retorna uma página HTML com links para os repositórios do projeto no GitHub.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Página HTML retornada com sucesso")
    })
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getSourcePage() {
        return """
                <!DOCTYPE html>
                <html lang="pt">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Repositórios do Projeto</title>
                    <style>
                        body { font-family: Arial, sans-serif; text-align: center; padding: 50px; }
                        .container { max-width: 600px; margin: auto; }
                        a {display: inline-block;
                            margin: 10px;
                            padding: 12px 24px;
                            font-size: 18px;
                            color: white;
                            text-decoration: none;
                            background-color: #007bff;
                            border-radius: 8px;}
                        a:hover { background-color: #0056b3; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h2>Repositórios do Projeto</h2>
                        <p>O código-fonte deste projeto está disponível no GitHub.</p>
                        <a href="https://github.com/GabrielGuilhermess/cadastro-backend" target="_blank">🔗 Backend</a>
                        <a href="https://github.com/GabrielGuilhermess/cadastro-frontend" target="_blank">🔗 Frontend</a>
                    </div>
                </body>
                </html>
                """;
    }
}
