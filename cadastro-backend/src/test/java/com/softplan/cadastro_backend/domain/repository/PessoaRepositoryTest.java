package com.softplan.cadastro_backend.domain.repository;

import com.softplan.cadastro_backend.domain.model.Pessoa;
import com.softplan.cadastro_backend.utils.ConstantsTestData;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para o repositório de Pessoa.
 */
@DataJpaTest(excludeAutoConfiguration = FlywayAutoConfiguration.class)
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EntityManager entityManager;

    /**
     * Testa a persistência de uma Pessoa válida.
     */
    @Test
    public void testSalvarPessoaValida() {
        Pessoa pessoa = Pessoa.builder()
                .nome(ConstantsTestData.NOME_VALIDO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .build();

        Pessoa saved = pessoaRepository.save(pessoa);
        entityManager.flush();

        assertNotNull(saved.getId(), "O ID deve ser gerado após salvar a pessoa.");
    }

    /**
     * Testa a busca de uma Pessoa pelo CPF.
     */
    @Test
    public void testFindByCpf() {
        Pessoa pessoa = Pessoa.builder()
                .nome(ConstantsTestData.NOME_VALIDO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .build();

        pessoaRepository.save(pessoa);
        entityManager.flush();

        Optional<Pessoa> found = pessoaRepository.findByCpf(ConstantsTestData.CPF_VALIDO);
        assertTrue(found.isPresent(), "Pessoa deve ser encontrada pelo CPF.");
    }

    /**
     * Verifica se o CPF é único, lançando exceção ao persistir duplicado.
     */
    @Test
    public void testCpfUnico() {
        Pessoa pessoa1 = Pessoa.builder()
                .nome(ConstantsTestData.NOME_VALIDO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .build();
        pessoaRepository.save(pessoa1);
        entityManager.flush();

        Pessoa pessoa2 = Pessoa.builder()
                .nome(ConstantsTestData.NOME_ALTERNATIVO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .build();

        assertThrows(DataIntegrityViolationException.class, () -> {
            pessoaRepository.save(pessoa2);
            entityManager.flush();
        }, "Deve lançar exceção ao tentar salvar uma pessoa com CPF duplicado.");
    }

    /**
     * Testa a listagem de todas as pessoas persistidas.
     */
    @Test
    public void testFindAllPessoas() {
        Pessoa pessoa1 = Pessoa.builder()
                .nome(ConstantsTestData.NOME_VALIDO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .build();

        Pessoa pessoa2 = Pessoa.builder()
                .nome(ConstantsTestData.NOME_ALTERNATIVO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO_ALTERNATIVO)
                .build();

        pessoaRepository.save(pessoa1);
        pessoaRepository.save(pessoa2);
        entityManager.flush();

        List<Pessoa> pessoas = pessoaRepository.findAll();
        assertEquals(2, pessoas.size(), "Deve retornar 2 pessoas.");
    }
}
