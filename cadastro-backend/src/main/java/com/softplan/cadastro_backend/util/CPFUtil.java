package com.softplan.cadastro_backend.util;

import lombok.experimental.UtilityClass;

/**
 * Classe utilitária para manipulação e validação de CPF.
 * <p>
 * Fornece métodos para limpar, validar e calcular dígitos verificadores de um CPF.
 * </p>
 */
@UtilityClass
public class CPFUtil {

    /**
     * Remove todos os caracteres não numéricos de um CPF.
     *
     * @param cpf a String que representa o CPF (pode estar formatado ou não).
     * @return o CPF contendo apenas dígitos numéricos ou null se a entrada for nula ou vazia.
     */
    public static String limparCPF(String cpf) {
        if (isNuloOuVazio(cpf)) {
            return null;
        }
        return cpf.replaceAll("[^\\d]", "");
    }

    /**
     * Valida um CPF.
     *
     * @param cpf a String que representa o CPF (pode estar formatado ou não).
     * @return true se o CPF for válido; false caso contrário.
     */
    public static boolean isCPFValido(String cpf) {
        String cpfNumeros = limparCPF(cpf);

        if (isNuloOuVazio(cpfNumeros) || !possuiTamanhoValido(cpfNumeros) || possuiTodosDigitosIguais(cpfNumeros)) {
            return false;
        }
        return isDigitosVerificadoresValidos(cpfNumeros);
    }

    /**
     * Verifica se o CPF é nulo ou vazio.
     *
     * @param cpf a String do CPF.
     * @return true se for nulo ou vazio; false caso contrário.
     */
    static boolean isNuloOuVazio(String cpf) {
        return cpf == null || cpf.trim().isEmpty();
    }

    /**
     * Verifica se o CPF possui tamanho válido (11 dígitos).
     *
     * @param cpfNumeros o CPF contendo somente dígitos.
     * @return true se possuir 11 dígitos; false caso contrário.
     */
    static boolean possuiTamanhoValido(String cpfNumeros) {
        return cpfNumeros.length() == 11;
    }

    /**
     * Verifica se todos os dígitos do CPF são iguais.
     *
     * @param cpfNumeros o CPF contendo somente dígitos.
     * @return true se todos os dígitos forem iguais; false caso contrário.
     */
    static boolean possuiTodosDigitosIguais(String cpfNumeros) {
        return cpfNumeros.chars().distinct().count() == 1;
    }

    /**
     * Valida os dígitos verificadores do CPF.
     *
     * @param cpfNumeros o CPF contendo somente dígitos.
     * @return true se os dígitos verificadores estiverem corretos; false caso contrário.
     */
    static boolean isDigitosVerificadoresValidos(String cpfNumeros) {
        int primeiroDigitoVerificador = calcularDigitoVerificador(cpfNumeros, 9, 10);
        if (primeiroDigitoVerificador != cpfNumeros.charAt(9) - '0') {
            return false;
        }
        int segundoDigitoVerificador = calcularDigitoVerificador(cpfNumeros, 10, 11);
        return segundoDigitoVerificador == cpfNumeros.charAt(10) - '0';
    }

    /**
     * Calcula o dígito verificador para o CPF.
     *
     * @param cpfNumeros  o CPF contendo somente dígitos.
     * @param tamanho     o número de dígitos a considerar para o cálculo.
     * @param pesoInicial o peso inicial para a multiplicação dos dígitos.
     * @return o dígito verificador calculado.
     */
    static int calcularDigitoVerificador(String cpfNumeros, int tamanho, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < tamanho; i++) {
            int digito = cpfNumeros.charAt(i) - '0';
            soma += digito * (pesoInicial - i);
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}
