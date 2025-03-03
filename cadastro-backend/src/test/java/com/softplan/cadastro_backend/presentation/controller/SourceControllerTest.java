package com.softplan.cadastro_backend.presentation.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes unitários para a classe {@link SourceController}.
 */
public class SourceControllerTest {

    private MockMvc mockMvc;

    /**
     * Configura o ambiente de teste antes de cada execução.
     */
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new SourceController()).build();
    }

    /**
     * Testa o endpoint que retorna a página de repositórios do projeto.
     *
     * @throws Exception em caso de erro durante a execução do teste.
     */
    @Test
    public void testGetSourcePage() throws Exception {
        mockMvc.perform(get("/source")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", Matchers.startsWith(MediaType.TEXT_HTML_VALUE)))
                .andExpect(content().string(Matchers.containsString("<title>Repositórios do Projeto</title>")))
                .andExpect(content().string(Matchers.containsString("https://github.com/GabrielGuilhermess/cadastro-backend")))
                .andExpect(content().string(Matchers.containsString("https://github.com/GabrielGuilhermess/cadastro-frontend")));
    }
}
