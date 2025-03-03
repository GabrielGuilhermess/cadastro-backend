package com.softplan.cadastro_backend.presentation.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

/**
 * Exceção para erros de validação de domínio.
 * <p>
 * Essa exceção contém uma lista de mensagens de erro que foram geradas durante a validação.
 * </p>
 */
@Getter
@Schema(description = "Exceção para erros de validação de domínio. Contém uma lista de mensagens de erro.")
public class DomainValidationException extends RuntimeException {

    /**
     * Lista de erros de validação.
     */
    @Schema(description = "Lista de erros de validação", example = "[\"Nome é obrigatório\", \"CPF inválido\"]")
    private final List<String> errors;

    /**
     * Construtor que cria a exceção a partir de uma lista de erros.
     *
     * @param errors Lista de mensagens de erro.
     */
    public DomainValidationException(List<String> errors) {
        super(String.join("; ", errors));
        this.errors = errors;
    }
}
