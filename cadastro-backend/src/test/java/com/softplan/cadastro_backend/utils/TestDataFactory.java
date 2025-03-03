package com.softplan.cadastro_backend.utils;

import com.softplan.cadastro_backend.application.dto.EnderecoDTO;
import com.softplan.cadastro_backend.application.dto.PessoaDTO;
import com.softplan.cadastro_backend.application.dto.PessoaDTOV2;
import com.softplan.cadastro_backend.domain.model.Endereco;
import com.softplan.cadastro_backend.domain.model.Pessoa;

/**
 * Fábrica de objetos para testes unitários.
 */
public class TestDataFactory {

    /**
     * Cria uma instância válida de Pessoa (versão sem endereço).
     *
     * @return uma Pessoa válida
     */
    public static Pessoa criarPessoaValida() {
        return Pessoa.builder()
                .nome(ConstantsTestData.NOME_VALIDO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .build();
    }

    /**
     * Cria uma instância alternativa de Pessoa (versão sem endereço).
     *
     * @return uma Pessoa com dados alternativos
     */
    public static Pessoa criarPessoaAlternativa() {
        return Pessoa.builder()
                .id(2L)
                .nome(ConstantsTestData.NOME_ALTERNATIVO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO_ALTERNATIVO)
                .build();
    }

    /**
     * Cria uma instância de Pessoa com endereço.
     *
     * @return uma Pessoa com um endereço associado
     */
    public static Pessoa criarPessoaComEndereco() {
        return Pessoa.builder()
                .nome(ConstantsTestData.NOME_VALIDO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .endereco(criarEndereco())
                .build();
    }

    /**
     * Cria uma instância de Pessoa sem endereço.
     *
     * @return uma Pessoa sem endereço (atributo <code>endereco</code> nulo)
     */
    public static Pessoa criarPessoaSemEndereco() {
        return Pessoa.builder()
                .id(2L)
                .nome(ConstantsTestData.NOME_ALTERNATIVO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO_ALTERNATIVO)
                .endereco(null)
                .build();
    }

    /**
     * Cria uma instância de Pessoa com dados atualizados.
     *
     * @return uma Pessoa com dados atualizados
     */
    public static Pessoa criarPessoaAtualizada() {
        return Pessoa.builder()
                .id(1L)
                .nome("Nome Atualizado")
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email("updated@email.com")
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade("Naturalidade Atualizada")
                .nacionalidade("Nacionalidade Atualizada")
                .cpf(ConstantsTestData.CPF_VALIDO)
                .endereco(criarEndereco())
                .build();
    }

    /**
     * Cria uma instância de Endereco.
     *
     * @return um objeto Endereco com dados válidos
     */
    public static Endereco criarEndereco() {
        return Endereco.builder()
                .logradouro(ConstantsTestData.LOGRADOURO)
                .numero(ConstantsTestData.NUMERO)
                .complemento(ConstantsTestData.COMPLEMENTO)
                .bairro(ConstantsTestData.BAIRRO)
                .cidade(ConstantsTestData.CIDADE)
                .estado(ConstantsTestData.ESTADO)
                .cep(ConstantsTestData.CEP)
                .build();
    }

    /**
     * Cria uma instância de EnderecoDTO.
     *
     * @return um objeto EnderecoDTO com dados válidos
     */
    public static EnderecoDTO criarEnderecoDTO() {
        return EnderecoDTO.builder()
                .logradouro(ConstantsTestData.LOGRADOURO)
                .numero(ConstantsTestData.NUMERO)
                .complemento(ConstantsTestData.COMPLEMENTO)
                .bairro(ConstantsTestData.BAIRRO)
                .cidade(ConstantsTestData.CIDADE)
                .estado(ConstantsTestData.ESTADO)
                .cep(ConstantsTestData.CEP)
                .build();
    }

    /**
     * Cria uma instância de PessoaDTO (versão sem endereço).
     *
     * @return um objeto PessoaDTO com dados válidos
     */
    public static PessoaDTO criarPessoaDTO() {
        return PessoaDTO.builder()
                .nome(ConstantsTestData.NOME_VALIDO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .build();
    }

    /**
     * Cria um DTO alternativo para Pessoa (versão 1) com dados diferentes.
     *
     * @return Objeto PessoaDTO com dados alternativos.
     */
    public static PessoaDTO criarPessoaDTOAlternativo() {
        return PessoaDTO.builder()
                .nome(ConstantsTestData.NOME_ALTERNATIVO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO_ALTERNATIVO)
                .build();
    }

    /**
     * Cria uma instância de PessoaDTOV2 (versão com endereço obrigatório).
     *
     * @return um objeto PessoaDTOV2 com dados válidos e endereço
     */
    public static PessoaDTOV2 criarPessoaDTOV2() {
        return PessoaDTOV2.builder()
                .nome(ConstantsTestData.NOME_VALIDO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .endereco(criarEnderecoDTO())
                .build();
    }

    /**
     * Cria uma instância de PessoaDTOV2 sem endereço.
     * Como o endereço é obrigatório, esta configuração deve gerar erro na validação.
     *
     * @return um objeto PessoaDTOV2 com o atributo <code>endereco</code> nulo
     */
    public static PessoaDTOV2 criarPessoaDTOV2SemEndereco() {
        return PessoaDTOV2.builder()
                .nome(ConstantsTestData.NOME_VALIDO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .endereco(null)
                .build();
    }
}
