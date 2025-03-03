package com.softplan.cadastro_backend.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa o endereço de uma pessoa.
 * <p>
 * Essa classe é embutida na entidade {@link Pessoa} e contém informações
 * como logradouro, número, complemento, bairro, cidade, estado e CEP.
 * </p>
 */
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "EnderecoBuilder")
@Schema(description = "Objeto de endereço embutido na Pessoa")
public class Endereco {

    @Schema(description = "Nome da rua, avenida, rodovia, etc.", example = "Rua das Flores")
    private String logradouro;

    @Schema(description = "Número do imóvel", example = "123")
    private String numero;

    @Schema(description = "Complemento do endereço, como bloco ou apartamento", example = "Apto 101")
    private String complemento;

    @Schema(description = "Bairro onde a pessoa reside", example = "Centro")
    private String bairro;

    @Schema(description = "Cidade onde a pessoa reside", example = "São Paulo")
    private String cidade;

    @Schema(description = "Estado onde a pessoa reside", example = "SP")
    private String estado;

    @Schema(description = "Código de Endereçamento Postal (CEP)", example = "01234-567")
    private String cep;
}
