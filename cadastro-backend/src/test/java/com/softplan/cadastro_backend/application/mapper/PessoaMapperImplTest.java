package com.softplan.cadastro_backend.application.mapper;

import com.softplan.cadastro_backend.application.dto.PessoaDTO;
import com.softplan.cadastro_backend.application.dto.PessoaDTOV2;
import com.softplan.cadastro_backend.domain.model.Endereco;
import com.softplan.cadastro_backend.domain.model.Pessoa;
import com.softplan.cadastro_backend.util.CPFUtil;
import com.softplan.cadastro_backend.utils.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a implementação {@link PessoaMapperImpl}.
 */
public class PessoaMapperImplTest {

    private PessoaMapperImpl pessoaMapper;

    /**
     * Configuração do mapper antes de cada teste.
     */
    @BeforeEach
    void setUp() {
        pessoaMapper = new PessoaMapperImpl();
    }

    /**
     * Testa a conversão de uma entidade {@link Pessoa} para um {@link PessoaDTO} (versão 1).
     */
    @Test
    public void testToDto() {
        Pessoa pessoa = TestDataFactory.criarPessoaComEndereco();

        PessoaDTO dto = pessoaMapper.toDto(pessoa);

        assertNotNull(dto, "O DTO não deve ser nulo");
        assertEquals(pessoa.getId(), dto.getId(), "ID não confere");
        assertEquals(pessoa.getNome(), dto.getNome(), "Nome não confere");
        assertEquals(pessoa.getSexo(), dto.getSexo(), "Sexo não confere");
        assertEquals(pessoa.getEmail(), dto.getEmail(), "Email não confere");
        assertEquals(pessoa.getDataNascimento(), dto.getDataNascimento(), "Data de nascimento não confere");
        assertEquals(pessoa.getCpf(), dto.getCpf(), "CPF não confere");
    }

    /**
     * Testa a conversão de uma entidade nula para {@link PessoaDTO}.
     */
    @Test
    public void testToDtoNulo() {
        PessoaDTO dtoNulo = pessoaMapper.toDto(null);
        assertNull(dtoNulo, "Se a entidade for nula, o DTO deve ser nulo");
    }

    /**
     * Testa a conversão de uma entidade {@link Pessoa} para um {@link PessoaDTOV2} (versão 2).
     */
    @Test
    public void testToDtoV2() {
        Pessoa pessoa = TestDataFactory.criarPessoaComEndereco();

        PessoaDTOV2 dto = pessoaMapper.toDtoV2(pessoa);

        assertNotNull(dto, "O DTO v2 não deve ser nulo");
        assertNotNull(dto.getEndereco(), "O endereço deve ser mapeado no DTO v2");
    }

    /**
     * Testa a conversão de uma entidade nula para {@link PessoaDTOV2}.
     */
    @Test
    public void testToDtoV2Nulo() {
        PessoaDTOV2 dto = pessoaMapper.toDtoV2(null);
        assertNull(dto, "Se a entidade for nula, o DTO v2 deve ser nulo");
    }

    /**
     * Testa a conversão de um {@link PessoaDTO} para uma entidade {@link Pessoa}.
     */
    @Test
    public void testToEntity() {
        PessoaDTO pessoaDTO = TestDataFactory.criarPessoaDTO();

        Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);

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
        Pessoa pessoa = pessoaMapper.toEntity((PessoaDTO) null);
        assertNull(pessoa, "Se o DTO for nulo, a entidade deve ser nula");

        Pessoa pessoaV2 = pessoaMapper.toEntity((PessoaDTOV2) null);
        assertNull(pessoaV2, "Se o DTO for nulo, a entidade deve ser nula");
    }


    /**
     * Testa a conversão de um {@link PessoaDTOV2} para uma entidade {@link Pessoa}.
     */
    @Test
    public void testToEntityV2() {
        PessoaDTOV2 pessoaDTOV2 = TestDataFactory.criarPessoaDTOV2();

        Pessoa pessoa = pessoaMapper.toEntity(pessoaDTOV2);

        assertNotNull(pessoa, "A entidade não deve ser nula");
        assertEquals(pessoaDTOV2.getId(), pessoa.getId(), "ID não confere");
        assertEquals(pessoaDTOV2.getNome(), pessoa.getNome(), "Nome não confere");
        assertEquals(pessoaDTOV2.getSexo(), pessoa.getSexo(), "Sexo não confere");
        assertEquals(pessoaDTOV2.getEmail(), pessoa.getEmail(), "Email não confere");
        assertEquals(pessoaDTOV2.getDataNascimento(), pessoa.getDataNascimento(), "Data de nascimento não confere");
        assertEquals(pessoaDTOV2.getCpf(), pessoa.getCpf(), "CPF não confere");
        assertNotNull(pessoa.getEndereco(), "O endereço deve ser mapeado na versão 2");
    }

    /**
     * Testa se uma exceção é lançada ao tentar mapear um {@link PessoaDTOV2} sem endereço.
     */
    @Test
    public void testToEntityV2SemEndereco() {
        PessoaDTOV2 pessoaDTOV2 = TestDataFactory.criarPessoaDTOV2SemEndereco();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> pessoaMapper.toEntity(pessoaDTOV2));

        assertEquals("Endereço é obrigatório para a versão 2", exception.getMessage(),
                "Deve lançar exceção informando que o endereço é obrigatório para v2");
    }


    /**
     * Verifica se o método merge atualiza os campos da entidade com os dados do DTO,
     * preservando o endereço já existente.
     */
    @Test
    public void testMergeAtualizaCamposMasPreservaEndereco() {
        Pessoa existing = TestDataFactory.criarPessoaComEndereco();
        Endereco enderecoOriginal = existing.getEndereco();

        PessoaDTO dto = TestDataFactory.criarPessoaDTO();
        dto.setNome("Novo Nome");
        dto.setCpf("52998224725"); // Um CPF válido conforme a lógica do CPFUtil

        Pessoa merged = pessoaMapper.merge(dto, existing);

        assertEquals(dto.getNome(), merged.getNome(), "O nome deve ser atualizado");
        assertEquals(dto.getSexo(), merged.getSexo(), "O sexo deve ser atualizado");
        assertEquals(dto.getEmail(), merged.getEmail(), "O email deve ser atualizado");
        assertEquals(dto.getDataNascimento(), merged.getDataNascimento(), "A data de nascimento deve ser atualizada");
        assertEquals(dto.getNaturalidade(), merged.getNaturalidade(), "A naturalidade deve ser atualizada");
        assertEquals(dto.getNacionalidade(), merged.getNacionalidade(), "A nacionalidade deve ser atualizada");
        assertEquals(CPFUtil.limparCPF(dto.getCpf()), merged.getCpf(), "O CPF deve ser atualizado");
        assertEquals(enderecoOriginal, merged.getEndereco(), "O endereço não deve ser modificado");
    }

    /**
     * Verifica se o método merge retorna a entidade existente inalterada quando o DTO é nulo.
     */
    @Test
    public void testMerge_NullDtoRetornaExisting() {
        Pessoa existing = TestDataFactory.criarPessoaComEndereco();
        Pessoa merged = pessoaMapper.merge(null, existing);
        assertSame(existing, merged, "Se o DTO for nulo, deve retornar a entidade existente");
    }

    /**
     * Verifica se o método merge lança uma exceção quando a entidade existente é nula.
     */
    @Test
    public void testMerge_NullExistingLancaExcecao() {
        PessoaDTO dto = TestDataFactory.criarPessoaDTO();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> pessoaMapper.merge(dto, null));
        assertEquals("A entidade existente não pode ser nula para atualização", exception.getMessage());
    }
}
