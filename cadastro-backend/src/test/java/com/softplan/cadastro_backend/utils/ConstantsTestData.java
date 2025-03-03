package com.softplan.cadastro_backend.utils;

import com.softplan.cadastro_backend.domain.enums.SexoEnum;

import java.time.LocalDate;

/**
 * Classe que contém constantes utilizadas para testes unitários.
 */
public class ConstantsTestData {

    public static final Long ID_VALIDO = 1L;
    public static final String NOME_VALIDO = "João Silva";
    public static final String NOME_ALTERNATIVO = "Juliana Souza";
    public static final String EMAIL_VALIDO = "joao@email.com";
    public static final LocalDate DATA_NASCIMENTO_VALIDA = LocalDate.of(1990, 5, 15);
    public static final String CPF_VALIDO = "15124127759";
    public static final String CPF_VALIDO_ALTERNATIVO = "52998224725";
    public static final String NATURALIDADE = "São Paulo";
    public static final String NACIONALIDADE = "Brasil";
    public static final SexoEnum SEXO_VALIDO = SexoEnum.MASCULINO;

    public static final String CPF_INVALIDO = "123456789";
    public static final String EMAIL_INVALIDO = "joaoemail.com";
    public static final LocalDate DATA_NASCIMENTO_FUTURA = LocalDate.of(2050, 1, 1);

    public static final String LOGRADOURO = "Rua dos Andradas";
    public static final String NUMERO = "123";
    public static final String COMPLEMENTO = "Apto 101";
    public static final String BAIRRO = "Centro";
    public static final String CIDADE = "Rio de Janeiro";
    public static final String ESTADO = "RJ";
    public static final String CEP = "01234-567";

    public static final String DESCRICAO_SEXO_NAO_DEFINIDO = "Não Definido";
    public static final String DESCRICAO_SEXO_MASCULINO = "Masculino";
    public static final String DESCRICAO_SEXO_FEMININO = "Feminino";
    public static final String DESCRICAO_SEXO_OUTROS = "Outros";
}
