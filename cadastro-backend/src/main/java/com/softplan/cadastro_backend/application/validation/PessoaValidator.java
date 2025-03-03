package com.softplan.cadastro_backend.application.validation;

import com.softplan.cadastro_backend.application.dto.PessoaDTO;
import com.softplan.cadastro_backend.application.dto.PessoaDTOV2;
import com.softplan.cadastro_backend.util.CPFUtil;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

/**
 * Classe utilitária para validação dos dados de Pessoa.
 * <p>
 * Fornece métodos para validar os dados de um {@link PessoaDTO} e {@link PessoaDTOV2}.
 * </p>
 */
@UtilityClass
public class PessoaValidator {

    /**
     * Valida os dados de um {@link PessoaDTO}.
     *
     * @param pessoaDTO o DTO contendo os dados da pessoa.
     * @throws IllegalArgumentException se algum dado obrigatório estiver ausente ou inválido.
     */
    public void validarPessoaDTO(PessoaDTO pessoaDTO) {
        if (pessoaDTO.getNome() == null || pessoaDTO.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (pessoaDTO.getDataNascimento() == null) {
            throw new IllegalArgumentException("Data de nascimento é obrigatória");
        }
        if (pessoaDTO.getDataNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento não pode ser futura");
        }
        if (!CPFUtil.isCPFValido(pessoaDTO.getCpf())) {
            throw new IllegalArgumentException("CPF inválido");
        }
        if (pessoaDTO.getEmail() != null && !pessoaDTO.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
    }

    /**
     * Valida os dados de um {@link PessoaDTOV2}.
     * <p>
     * Além de validar os dados comuns de PessoaDTO, verifica se o endereço foi informado.
     * </p>
     *
     * @param pessoaDTOV2 o DTO da versão 2 contendo os dados da pessoa.
     * @throws IllegalArgumentException se o endereço for nulo.
     */
    public void validarPessoaDTOV2(PessoaDTOV2 pessoaDTOV2) {
        validarPessoaDTO(pessoaDTOV2);
        if (pessoaDTOV2.getEndereco() == null) {
            throw new IllegalArgumentException("Endereço é obrigatório para a versão 2");
        }
    }
}
