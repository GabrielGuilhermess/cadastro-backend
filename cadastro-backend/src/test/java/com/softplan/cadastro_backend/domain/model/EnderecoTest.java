package com.softplan.cadastro_backend.domain.model;

import com.softplan.cadastro_backend.utils.ConstantsTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testes unitários para a entidade {@link Endereco}.
 */
public class EnderecoTest {

    /**
     * Testa a criação de um objeto Endereco com dados preenchidos.
     */
    @Test
    public void testCriarEnderecoComDados() {
        Endereco endereco = Endereco.builder()
                .logradouro(ConstantsTestData.LOGRADOURO)
                .numero(ConstantsTestData.NUMERO)
                .complemento(ConstantsTestData.COMPLEMENTO)
                .bairro(ConstantsTestData.BAIRRO)
                .cidade(ConstantsTestData.CIDADE)
                .estado(ConstantsTestData.ESTADO)
                .cep(ConstantsTestData.CEP)
                .build();

        assertNotNull(endereco, "O objeto Endereco não deve ser nulo quando criado com dados.");
    }

    /**
     * Testa a criação de um objeto Endereco sem dados.
     */
    @Test
    public void testCriarEnderecoSemDados() {
        Endereco endereco = Endereco.builder().build();

        assertNotNull(endereco, "O objeto Endereco deve ser criado mesmo sem dados.");
    }
}
