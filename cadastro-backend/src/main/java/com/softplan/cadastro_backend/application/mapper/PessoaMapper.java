package com.softplan.cadastro_backend.application.mapper;

import com.softplan.cadastro_backend.application.dto.PessoaDTO;
import com.softplan.cadastro_backend.application.dto.PessoaDTOV2;
import com.softplan.cadastro_backend.domain.model.Pessoa;

/**
 * Interface responsável pelo mapeamento entre a entidade {@link Pessoa}
 * e seus respectivos DTOs (versões 1 e 2).
 */
public interface PessoaMapper {

    /**
     * Converte uma entidade {@link Pessoa} para um {@link PessoaDTO} (versão 1).
     *
     * @param pessoa entidade a ser convertida.
     * @return DTO correspondente (não manipula o endereço).
     */
    PessoaDTO toDto(Pessoa pessoa);

    /**
     * Converte uma entidade {@link Pessoa} para um {@link PessoaDTOV2} (versão 2),
     * onde o endereço é obrigatório.
     *
     * @param pessoa entidade a ser convertida.
     * @return DTO correspondente com endereço.
     */
    PessoaDTOV2 toDtoV2(Pessoa pessoa);

    /**
     * Converte um {@link PessoaDTO} para a entidade {@link Pessoa}.
     * Utilizado para criação, onde o endereço não é informado.
     *
     * @param pessoaDTO DTO contendo os dados da pessoa.
     * @return Entidade Pessoa com o endereço não alterado (ou seja, null).
     */
    Pessoa toEntity(PessoaDTO pessoaDTO);

    /**
     * Converte um {@link PessoaDTOV2} para a entidade {@link Pessoa}.
     * Nesta versão, o endereço é obrigatório.
     *
     * @param pessoaDTOV2 DTO contendo os dados da pessoa.
     * @return Entidade Pessoa com endereço preenchido.
     */
    Pessoa toEntity(PessoaDTOV2 pessoaDTOV2);

    /**
     * Atualiza (merge) uma entidade existente com os dados do {@link PessoaDTO},
     * sem sobrescrever o endereço já existente.
     *
     * @param dto      Dados da pessoa (versão 1) a serem mesclados.
     * @param existing Entidade já existente.
     * @return A entidade atualizada.
     * @throws IllegalArgumentException se a entidade existente for nula.
     */
    Pessoa merge(PessoaDTO dto, Pessoa existing);
}
