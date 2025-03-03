package com.softplan.cadastro_backend.presentation.exception;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes unitários para a classe {@link DomainValidationException}.
 */
public class DomainValidationExceptionTest {

    /**
     * Testa a criação da exceção com uma lista de erros.
     */
    @Test
    public void testCriarExcecaoComListaDeErros() {
        List<String> erros = List.of("Nome é obrigatório", "CPF inválido");
        DomainValidationException exception = new DomainValidationException(erros);

        assertEquals(erros, exception.getErrors(), "A lista de erros armazenada deve ser a mesma passada no construtor.");
        String expectedMessage = "Nome é obrigatório; CPF inválido";
        assertEquals(expectedMessage, exception.getMessage(), "A mensagem da exceção deve concatenar os erros corretamente.");
    }

    /**
     * Testa a criação da exceção com uma lista vazia de erros.
     */
    @Test
    public void testCriarExcecaoComListaVazia() {
        DomainValidationException exception = new DomainValidationException(List.of());

        assertTrue(exception.getErrors().isEmpty(), "A lista de erros deve estar vazia.");
        assertEquals("", exception.getMessage(), "A mensagem da exceção deve ser vazia quando não há erros.");
    }

    /**
     * Testa a criação da exceção com um único erro.
     */
    @Test
    public void testCriarExcecaoComErroUnico() {
        DomainValidationException exception = new DomainValidationException(List.of("CPF inválido"));

        assertEquals(1, exception.getErrors().size(), "A lista de erros deve conter exatamente um erro.");
        assertEquals("CPF inválido", exception.getErrors().get(0), "O erro armazenado deve ser 'CPF inválido'.");
        assertEquals("CPF inválido", exception.getMessage(), "A mensagem da exceção deve ser o próprio erro.");
    }
}
