package com.softplan.cadastro_backend.domain.model;

import com.softplan.cadastro_backend.utils.ConstantsTestData;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a entidade {@link Pessoa}.
 */
public class PessoaTest {

    /**
     * Testa a criação de uma Pessoa com dados válidos.
     */
    @Test
    public void testCriarPessoaValida() {
        Pessoa pessoa = Pessoa.builder()
                .nome(ConstantsTestData.NOME_VALIDO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .build();

        assertNotNull(pessoa, "Pessoa válida não deveria ser nula.");
        assertEquals(ConstantsTestData.NOME_VALIDO, pessoa.getNome(), "Nome da pessoa não corresponde.");
    }

    /**
     * Testa se os métodos de callback (prePersist) configuram os timestamps corretamente.
     */
    @Test
    public void testPrePersistSetsTimestamps() {
        Pessoa pessoa = new Pessoa();
        pessoa.prePersist();

        assertNotNull(pessoa.getDataCadastro(), "Data de cadastro não pode ser nula após prePersist.");
        assertNotNull(pessoa.getDataAtualizacao(), "Data de atualização não pode ser nula após prePersist.");

        LocalDateTime agora = LocalDateTime.now();
        assertTrue(pessoa.getDataCadastro().isBefore(agora.plusSeconds(1)), "Data de cadastro deve estar próxima do tempo atual.");
        assertTrue(pessoa.getDataAtualizacao().isBefore(agora.plusSeconds(1)), "Data de atualização deve estar próxima do tempo atual.");
    }

    /**
     * Testa se o método preUpdate atualiza a data de atualização corretamente.
     */
    @Test
    public void testPreUpdateUpdatesDataAtualizacao() {
        Pessoa pessoa = new Pessoa();
        LocalDateTime dataAntiga = LocalDateTime.of(2000, 1, 1, 0, 0);
        pessoa.setDataAtualizacao(dataAntiga);

        pessoa.preUpdate();
        assertNotNull(pessoa.getDataAtualizacao(), "Data de atualização não pode ser nula após preUpdate.");
        assertTrue(pessoa.getDataAtualizacao().isAfter(dataAntiga), "Data de atualização deve ser atualizada para um valor posterior ao antigo.");
    }
}
