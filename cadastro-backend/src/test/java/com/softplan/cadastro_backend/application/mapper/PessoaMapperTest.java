package com.softplan.cadastro_backend.application.mapper;

import com.softplan.cadastro_backend.application.dto.PessoaDTO;
import com.softplan.cadastro_backend.application.dto.PessoaDTOV2;
import com.softplan.cadastro_backend.domain.model.Pessoa;
import com.softplan.cadastro_backend.utils.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a interface {@link PessoaMapper}.
 * <p>
 * Verifica se a implementação do mapeamento atende aos contratos esperados.
 */
public class PessoaMapperTest {

    private PessoaMapper mapper;

    /**
     * Configura a implementação concreta do mapper para os testes.
     */
    @BeforeEach
    public void setup() {
        mapper = new PessoaMapperImpl();
    }

    /**
     * Testa a conversão de uma entidade {@link Pessoa} para um {@link PessoaDTO}.
     */
    @Test
    public void testToDto() {
        Pessoa pessoa = TestDataFactory.criarPessoaComEndereco();

        PessoaDTO dto = mapper.toDto(pessoa);

        assertNotNull(dto, "O DTO não deve ser nulo");
        assertEquals(pessoa.getId(), dto.getId(), "ID não confere");
        assertEquals(pessoa.getNome(), dto.getNome(), "Nome não confere");
        assertEquals(pessoa.getSexo(), dto.getSexo(), "Sexo não confere");
        assertEquals(pessoa.getEmail(), dto.getEmail(), "Email não confere");
        assertEquals(pessoa.getDataNascimento(), dto.getDataNascimento(), "Data de nascimento não confere");
        assertEquals(pessoa.getNaturalidade(), dto.getNaturalidade(), "Naturalidade não confere");
        assertEquals(pessoa.getNacionalidade(), dto.getNacionalidade(), "Nacionalidade não confere");
        assertEquals(pessoa.getCpf(), dto.getCpf(), "CPF não confere");
    }

    /**
     * Testa a conversão de uma entidade nula para {@link PessoaDTO}.
     */
    @Test
    public void testToDtoPessoaNula() {
        PessoaDTO dto = mapper.toDto(null);
        assertNull(dto, "Se a pessoa for nula, o DTO também deve ser nulo.");
    }

    /**
     * Testa a conversão de uma entidade {@link Pessoa} para um {@link PessoaDTOV2} com endereço.
     */
    @Test
    public void testToDtoV2_ComEndereco() {
        Pessoa pessoa = TestDataFactory.criarPessoaComEndereco();

        PessoaDTOV2 dtoV2 = mapper.toDtoV2(pessoa);

        assertNotNull(dtoV2, "O DTO v2 não deve ser nulo");
        assertNotNull(dtoV2.getEndereco(), "Endereço do DTO v2 não deve ser nulo");
    }

    /**
     * Testa a conversão de uma entidade nula para {@link PessoaDTOV2}.
     */
    @Test
    public void testToDtoV2Nulo() {
        PessoaDTOV2 dto = mapper.toDtoV2(null);
        assertNull(dto, "Se a pessoa for nula, o DTO v2 também deve ser nulo.");
    }

    /**
     * Testa a conversão de um {@link PessoaDTO} para uma entidade {@link Pessoa}.
     */
    @Test
    public void testToEntity() {
        PessoaDTO pessoaDTO = TestDataFactory.criarPessoaDTO();

        Pessoa pessoa = mapper.toEntity(pessoaDTO);

        assertNotNull(pessoa, "A entidade não deve ser nula");
        assertEquals(pessoaDTO.getId(), pessoa.getId(), "ID não confere");
        assertEquals(pessoaDTO.getNome(), pessoa.getNome(), "Nome não confere");
        assertEquals(pessoaDTO.getSexo(), pessoa.getSexo(), "Sexo não confere");
        assertEquals(pessoaDTO.getEmail(), pessoa.getEmail(), "Email não confere");
        assertEquals(pessoaDTO.getDataNascimento(), pessoa.getDataNascimento(), "Data de nascimento não confere");
        assertEquals(pessoaDTO.getCpf(), pessoa.getCpf(), "CPF não confere");
    }

    /**
     * Testa a conversão de um objeto nulo para uma entidade.
     */
    @Test
    public void testToEntityNulo() {
        Pessoa pessoa = mapper.toEntity((PessoaDTO) null);
        assertNull(pessoa, "Se o DTO for nulo, a entidade também deve ser nula.");
    }

    /**
     * Testa a conversão de um {@link PessoaDTOV2} para uma entidade {@link Pessoa}.
     */
    @Test
    public void testToEntityV2() {
        PessoaDTOV2 pessoaDTOV2 = TestDataFactory.criarPessoaDTOV2();

        Pessoa pessoa = mapper.toEntity(pessoaDTOV2);

        assertNotNull(pessoa, "A entidade não deve ser nula");
        assertEquals(pessoaDTOV2.getId(), pessoa.getId(), "ID não confere");
        assertEquals(pessoaDTOV2.getNome(), pessoa.getNome(), "Nome não confere");
        assertEquals(pessoaDTOV2.getSexo(), pessoa.getSexo(), "Sexo não confere");
        assertEquals(pessoaDTOV2.getEmail(), pessoa.getEmail(), "Email não confere");
        assertEquals(pessoaDTOV2.getDataNascimento(), pessoa.getDataNascimento(), "Data de nascimento não confere");
        assertEquals(pessoaDTOV2.getCpf(), pessoa.getCpf(), "CPF não confere");
        assertNotNull(pessoa.getEndereco(), "O endereço deve ser mapeado corretamente na versão 2");
    }

    /**
     * Testa se uma exceção é lançada ao tentar mapear um {@link PessoaDTOV2} sem endereço.
     */
    @Test
    public void testToEntityV2SemEndereco() {
        PessoaDTOV2 pessoaDTOV2 = TestDataFactory.criarPessoaDTOV2SemEndereco();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mapper.toEntity(pessoaDTOV2));

        assertEquals("Endereço é obrigatório para a versão 2", exception.getMessage(),
                "Deve lançar exceção com mensagem informando que o endereço é obrigatório para v2");
    }
}
