package com.softplan.cadastro_backend.domain.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * Enumeração dos sexos disponíveis para uma pessoa.
 * <p>
 * Cada valor possui uma descrição associada que pode ser utilizada para exibição.
 */
@Getter
@Schema(description = "Enumeração dos sexos disponíveis para uma pessoa.")
public enum SexoEnum {

    @Schema(description = "Sexo não definido ou preferiu não informar")
    NAO_DEFINIDO("Não Definido"),

    @Schema(description = "Sexo masculino")
    MASCULINO("Masculino"),

    @Schema(description = "Sexo feminino")
    FEMININO("Feminino"),

    @Schema(description = "Outros gêneros não listados explicitamente")
    OUTROS("Outros");

    /**
     * Descrição do sexo.
     */
    private final String descricao;

    /**
     * Construtor do enum que atribui a descrição.
     *
     * @param descricao a descrição do sexo.
     */
    SexoEnum(String descricao) {
        this.descricao = descricao;
    }
}
