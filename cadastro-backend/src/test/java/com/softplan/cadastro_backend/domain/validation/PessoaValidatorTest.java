package com.softplan.cadastro_backend.domain.validation;

import com.softplan.cadastro_backend.application.dto.PessoaDTO;
import com.softplan.cadastro_backend.application.dto.PessoaDTOV2;
import com.softplan.cadastro_backend.application.validation.PessoaValidator;
import com.softplan.cadastro_backend.utils.ConstantsTestData;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a validação de objetos Pessoa.
 */
public class PessoaValidatorTest {

    /**
     * Valida um objeto PessoaDTO com dados válidos.
     */
    @Test
    public void testValidarPessoaDTOComDadosValidos() {
        PessoaDTO pessoaDTO = PessoaDTO.builder()
                .id(ConstantsTestData.ID_VALIDO)
                .nome(ConstantsTestData.NOME_VALIDO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .naturalidade(ConstantsTestData.NATURALIDADE)
                .nacionalidade(ConstantsTestData.NACIONALIDADE)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .build();

        assertDoesNotThrow(() -> PessoaValidator.validarPessoaDTO(pessoaDTO));
    }

    /**
     * Verifica se a validação de PessoaDTO lança exceção quando o nome é inválido.
     */
    @Test
    public void testValidarPessoaDTOComNomeInvalido() {
        PessoaDTO pessoaDTO = PessoaDTO.builder()
                .nome("   ")
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                PessoaValidator.validarPessoaDTO(pessoaDTO)
        );

        assertEquals("Nome é obrigatório", exception.getMessage());
    }

    /**
     * Verifica se a validação de PessoaDTO lança exceção quando a data de nascimento é futura.
     */
    @Test
    public void testValidarPessoaDTOComDataNascimentoFutura() {
        PessoaDTO pessoaDTO = PessoaDTO.builder()
                .nome(ConstantsTestData.NOME_VALIDO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(LocalDate.of(2050, 1, 1))
                .cpf(ConstantsTestData.CPF_VALIDO)
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                PessoaValidator.validarPessoaDTO(pessoaDTO)
        );

        assertEquals("Data de nascimento não pode ser futura", exception.getMessage());
    }

    /**
     * Verifica se a validação de PessoaDTOV2 lança exceção quando o endereço é nulo.
     */
    @Test
    public void testValidarPessoaDTOV2SemEndereco() {
        PessoaDTOV2 pessoaDTOV2 = PessoaDTOV2.builder()
                .nome(ConstantsTestData.NOME_VALIDO)
                .sexo(ConstantsTestData.SEXO_VALIDO)
                .email(ConstantsTestData.EMAIL_VALIDO)
                .dataNascimento(ConstantsTestData.DATA_NASCIMENTO_VALIDA)
                .cpf(ConstantsTestData.CPF_VALIDO)
                .endereco(null)
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                PessoaValidator.validarPessoaDTOV2(pessoaDTOV2)
        );

        assertEquals("Endereço é obrigatório para a versão 2", exception.getMessage());
    }

    @Test
    public void testValidarPessoaDTO_NomeVazio() {
        PessoaDTO dto = PessoaDTO.builder()
                .nome("   ")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .cpf("52998224725")
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> PessoaValidator.validarPessoaDTO(dto));
        assertEquals("Nome é obrigatório", exception.getMessage());
    }

    @Test
    public void testValidarPessoaDTO_DataNascimentoNula() {
        PessoaDTO dto = PessoaDTO.builder()
                .nome("Gabriel")
                .dataNascimento(null)
                .cpf("52998224725")
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> PessoaValidator.validarPessoaDTO(dto));
        assertEquals("Data de nascimento é obrigatória", exception.getMessage());
    }

    @Test
    public void testValidarPessoaDTO_DataNascimentoFutura() {
        PessoaDTO dto = PessoaDTO.builder()
                .nome("Gabriel")
                .dataNascimento(LocalDate.now().plusDays(1))
                .cpf("52998224725")
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> PessoaValidator.validarPessoaDTO(dto));
        assertEquals("Data de nascimento não pode ser futura", exception.getMessage());
    }

    @Test
    public void testValidarPessoaDTO_CPFInvalido() {
        PessoaDTO dto = PessoaDTO.builder()
                .nome("Gabriel")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .cpf("123") // CPF inválido
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> PessoaValidator.validarPessoaDTO(dto));
        assertEquals("CPF inválido", exception.getMessage());
    }

    @Test
    public void testValidarPessoaDTO_EmailInvalido() {
        PessoaDTO dto = PessoaDTO.builder()
                .nome("Gabriel")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .cpf("52998224725")
                .email("gabrielemail.com") // email sem @
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> PessoaValidator.validarPessoaDTO(dto));
        assertEquals("Email inválido", exception.getMessage());
    }

    @Test
    public void testValidarPessoaDTO_Valido() {
        PessoaDTO dto = PessoaDTO.builder()
                .nome("Gabriel")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .cpf("52998224725") // CPF válido, conforme a lógica do CPFUtil
                .email("gabriel@email.com")
                .build();

        // Não deve lançar exceção
        assertDoesNotThrow(() -> PessoaValidator.validarPessoaDTO(dto));
    }
}
