package com.softplan.cadastro_backend.application.mapper;

import com.softplan.cadastro_backend.application.dto.EnderecoDTO;
import com.softplan.cadastro_backend.domain.model.Endereco;
import com.softplan.cadastro_backend.utils.ConstantsTestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe utilitária {@link EnderecoMapper}.
 */
public class EnderecoMapperTest {

    /**
     * Testa a conversão de um {@link Endereco} válido para um {@link EnderecoDTO}.
     */
    @Test
    public void testToDtoEnderecoValido() {
        Endereco endereco = Endereco.builder()
                .logradouro(ConstantsTestData.LOGRADOURO)
                .numero(ConstantsTestData.NUMERO)
                .complemento(ConstantsTestData.COMPLEMENTO)
                .bairro(ConstantsTestData.BAIRRO)
                .cidade(ConstantsTestData.CIDADE)
                .estado(ConstantsTestData.ESTADO)
                .cep(ConstantsTestData.CEP)
                .build();

        EnderecoDTO enderecoDTO = EnderecoMapper.toDto(endereco);

        assertNotNull(enderecoDTO, "O DTO não deve ser nulo");
        assertEquals(endereco.getLogradouro(), enderecoDTO.getLogradouro(), "Logradouro não confere");
        assertEquals(endereco.getNumero(), enderecoDTO.getNumero(), "Número não confere");
        assertEquals(endereco.getComplemento(), enderecoDTO.getComplemento(), "Complemento não confere");
        assertEquals(endereco.getBairro(), enderecoDTO.getBairro(), "Bairro não confere");
        assertEquals(endereco.getCidade(), enderecoDTO.getCidade(), "Cidade não confere");
        assertEquals(endereco.getEstado(), enderecoDTO.getEstado(), "Estado não confere");
        assertEquals(endereco.getCep(), enderecoDTO.getCep(), "CEP não confere");
    }

    /**
     * Testa se o método {@link EnderecoMapper#toDto(Endereco)} retorna null quando o endereço é nulo.
     */
    @Test
    public void testToDtoEnderecoNulo() {
        EnderecoDTO enderecoDTO = EnderecoMapper.toDto(null);
        assertNull(enderecoDTO, "Se o endereço for nulo, o DTO deve ser nulo.");
    }

    /**
     * Testa o mapeamento para nulo ao converter um endereço nulo.
     */
    @Test
    public void testMapDtoToEnderecoNulo() {
        EnderecoDTO endereco = EnderecoMapper.toDto(null);
        assertNull(endereco, "Se o endereço for nulo, o DTO deve ser nulo.");
    }
}
