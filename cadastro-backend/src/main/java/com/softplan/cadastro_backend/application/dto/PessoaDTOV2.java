package com.softplan.cadastro_backend.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Representa os dados de uma pessoa cadastrada (V2). Nesta versão, o endereço é obrigatório.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor  // Adicionado para permitir a desserialização
@AllArgsConstructor // Opcional, se necessário
@ToString(callSuper = true)
@Schema(description = "Representa os dados de uma pessoa cadastrada (V2). Nesta versão, o endereço é obrigatório.")
public class PessoaDTOV2 extends PessoaDTO {

    @Schema(description = "Endereço residencial da pessoa (obrigatório nesta versão)")
    private EnderecoDTO endereco;
}
