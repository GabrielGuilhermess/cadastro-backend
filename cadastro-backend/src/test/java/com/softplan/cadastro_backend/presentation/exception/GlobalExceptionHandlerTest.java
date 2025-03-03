package com.softplan.cadastro_backend.presentation.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testes unitários para a classe {@link GlobalExceptionHandler}.
 */
public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    /**
     * Configura o ambiente de teste antes de cada execução.
     */
    @BeforeEach
    public void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    /**
     * Testa o tratamento de exceção quando uma pessoa não é encontrada.
     */
    @Test
    public void testHandlePessoaNaoEncontrada() {
        PessoaNaoEncontradaException ex = new PessoaNaoEncontradaException("15124127759");
        ResponseEntity<Map<String, Object>> response = exceptionHandler.handlePessoaNaoEncontrada(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Deveria retornar 404 Not Found");
        assertNotNull(response.getBody());
        assertEquals("Pessoa com CPF 15124127759 não encontrada.", response.getBody().get("message"));
        assertEquals(404, response.getBody().get("status"));
    }

    /**
     * Testa o tratamento genérico de exceções.
     */
    @Test
    public void testHandleGenericException() {
        Exception ex = new RuntimeException("Erro desconhecido");
        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleGenericException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode(), "Deveria retornar 500 Internal Server Error");
        assertNotNull(response.getBody());
        assertEquals("Ocorreu um erro inesperado. Contate o suporte.", response.getBody().get("message"));
        assertEquals(500, response.getBody().get("status"));
    }

    @Test
    public void testHandleValidationException() {
        String errorMessage = "Nome é obrigatório";
        IllegalArgumentException ex = new IllegalArgumentException(errorMessage);

        ResponseEntity<Map<String, Object>> response = exceptionHandler.handleValidationException(ex);

        assertEquals(400, response.getStatusCodeValue());

        Map<String, Object> body = response.getBody();
        assertNotNull(body, "O corpo da resposta não deve ser nulo");
        assertEquals(400, body.get("status"));
        assertEquals("Bad Request", body.get("error"));
        assertEquals(errorMessage, body.get("message"));
    }
}
