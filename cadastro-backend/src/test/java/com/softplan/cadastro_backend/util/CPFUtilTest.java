package com.softplan.cadastro_backend.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para os métodos utilitários de CPF.
 */
public class CPFUtilTest {

    /**
     * Testa se o CPF é considerado válido.
     */
    @Test
    public void testCPFValido() {
        assertTrue(CPFUtil.isCPFValido("529.982.247-25"));
        assertTrue(CPFUtil.isCPFValido("52998224725"));
    }

    /**
     * Testa se o CPF é considerado inválido.
     */
    @Test
    public void testCPFInvalido() {
        assertFalse(CPFUtil.isCPFValido("111.111.111-11"));
        assertFalse(CPFUtil.isCPFValido("529.982.247-26"));
        assertFalse(CPFUtil.isCPFValido("123456789"));
        assertFalse(CPFUtil.isCPFValido(null));
        assertFalse(CPFUtil.isCPFValido("   "));
    }

    /**
     * Testa o método que verifica se a String é nula ou vazia.
     */
    @Test
    public void testIsNuloOuVazio() {
        assertTrue(CPFUtil.isNuloOuVazio(null));
        assertTrue(CPFUtil.isNuloOuVazio("   "));
        assertFalse(CPFUtil.isNuloOuVazio("529.982.247-25"));
    }

    /**
     * Testa a extração dos dígitos do CPF.
     */
    @Test
    public void testExtrairDigitos() {
        String resultado = CPFUtil.limparCPF("529.982.247-25");
        assertEquals("52998224725", resultado);
    }

    /**
     * Testa a verificação do tamanho do CPF.
     */
    @Test
    public void testPossuiTamanhoValido() {
        assertTrue(CPFUtil.possuiTamanhoValido("52998224725"));
        assertFalse(CPFUtil.possuiTamanhoValido("5299822472"));
    }

    /**
     * Testa se o CPF possui todos os dígitos iguais.
     */
    @Test
    public void testPossuiTodosDigitosIguais() {
        assertTrue(CPFUtil.possuiTodosDigitosIguais("11111111111"));
        assertFalse(CPFUtil.possuiTodosDigitosIguais("15124127759"));
    }

    /**
     * Testa o cálculo do dígito verificador do CPF.
     */
    @Test
    public void testCalcularDigitoVerificador() {
        int primeiro = CPFUtil.calcularDigitoVerificador("52998224725", 9, 10);
        assertEquals(2, primeiro);
        int segundo = CPFUtil.calcularDigitoVerificador("52998224725", 10, 11);
        assertEquals(5, segundo);
    }

    /**
     * Testa a validação dos dígitos verificadores do CPF.
     */
    @Test
    public void testIsDigitosVerificadoresValidos() {
        assertTrue(CPFUtil.isDigitosVerificadoresValidos("52998224725"));
        assertFalse(CPFUtil.isDigitosVerificadoresValidos("52998224726"));
        assertTrue(CPFUtil.isDigitosVerificadoresValidos("76570555960"));
        assertFalse(CPFUtil.isDigitosVerificadoresValidos("76570555970"));
        assertFalse(CPFUtil.isDigitosVerificadoresValidos("76570555971"));
    }
}
