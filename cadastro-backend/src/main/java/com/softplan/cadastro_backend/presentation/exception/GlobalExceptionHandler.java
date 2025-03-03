package com.softplan.cadastro_backend.presentation.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Manipulador global de exceções da API.
 * <p>
 * Essa classe intercepta exceções lançadas durante a execução dos endpoints e
 * retorna respostas padronizadas com informações como timestamp, status e mensagem.
 * </p>
 */
@RestControllerAdvice
@Schema(description = "Manipulador global de exceções da API.")
public class GlobalExceptionHandler {

    /**
     * Trata exceções de pessoa não encontrada e retorna um erro 404.
     *
     * @param ex Exceção de pessoa não encontrada.
     * @return Resposta padronizada com status 404.
     */
    @ExceptionHandler(PessoaNaoEncontradaException.class)
    public ResponseEntity<Map<String, Object>> handlePessoaNaoEncontrada(PessoaNaoEncontradaException ex) {
        Map<String, Object> errorBody = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.NOT_FOUND.value(),
                "error", "Not Found",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
    }

    /**
     * Trata exceções de validação (IllegalArgumentException) e retorna um erro 400.
     *
     * @param ex Exceção de argumento inválido.
     * @return Resposta padronizada com status 400.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(IllegalArgumentException ex) {
        Map<String, Object> errorBody = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", "Bad Request",
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    /**
     * Trata exceções genéricas e retorna um erro 500.
     *
     * @param ex Exceção genérica.
     * @return Resposta padronizada com status 500.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> errorBody = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error", "Internal Server Error",
                "message", "Ocorreu um erro inesperado. Contate o suporte."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
    }
}
