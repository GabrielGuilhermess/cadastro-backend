package com.softplan.cadastro_backend.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * Representa um endereço completo de uma pessoa.
 */
@Data
@Builder
@Schema(description = "Representa um endereço completo de uma pessoa.")
public class EnderecoDTO {

    @Schema(description = "Nome da rua, avenida ou logradouro", example = "Rua das Flores")
    private String logradouro;

    @Schema(description = "Número do imóvel", example = "123")
    private String numero;

    @Schema(description = "Informações adicionais sobre o endereço (opcional)", example = "Apto 101")
    private String complemento;

    @Schema(description = "Bairro onde a pessoa reside", example = "Centro")
    private String bairro;

    @Schema(description = "Cidade de residência", example = "São Paulo")
    private String cidade;

    @Schema(description = "Estado de residência (UF)", example = "SP")
    private String estado;

    @Schema(description = "Código de Endereçamento Postal (CEP)", example = "01234-567")
    private String cep;
}
