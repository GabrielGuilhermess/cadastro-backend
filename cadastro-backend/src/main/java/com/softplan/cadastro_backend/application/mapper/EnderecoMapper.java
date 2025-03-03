package com.softplan.cadastro_backend.application.mapper;

import com.softplan.cadastro_backend.application.dto.EnderecoDTO;
import com.softplan.cadastro_backend.domain.model.Endereco;
import lombok.experimental.UtilityClass;

/**
 * Classe utilitária para conversão entre {@link Endereco} e {@link EnderecoDTO}.
 */
@UtilityClass
public class EnderecoMapper {

    public EnderecoDTO toDto(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return EnderecoDTO.builder()
                .logradouro(endereco.getLogradouro())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .build();
    }

    public Endereco toEntity(EnderecoDTO enderecoDTO) {
        if (enderecoDTO == null) {
            return null;
        }
        return Endereco.builder()
                .logradouro(enderecoDTO.getLogradouro())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .bairro(enderecoDTO.getBairro())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();
    }
}
