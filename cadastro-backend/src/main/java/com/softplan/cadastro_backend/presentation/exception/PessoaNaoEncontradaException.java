package com.softplan.cadastro_backend.presentation.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma pessoa não é encontrada no sistema.
 * <p>
 * Essa exceção pode ser lançada ao buscar uma pessoa por CPF ou ID.
 * </p>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@Schema(description = "Exceção lançada quando uma pessoa não é encontrada no sistema.")
public class PessoaNaoEncontradaException extends RuntimeException {

    /**
     * Construtor que cria uma exceção para o caso de pessoa não encontrada pelo CPF.
     *
     * @param cpf CPF da pessoa não encontrada.
     */
    public PessoaNaoEncontradaException(String cpf) {
        super("Pessoa com CPF " + cpf + " não encontrada.");
    }

    /**
     * Construtor que cria uma exceção para o caso de pessoa não encontrada pelo ID.
     *
     * @param id ID da pessoa não encontrada.
     */
    public PessoaNaoEncontradaException(Long id) {
        super("Pessoa com ID " + id + " não encontrada.");
    }
}
