package com.softplan.cadastro_backend.application.dto;

import com.softplan.cadastro_backend.domain.enums.SexoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Representa os dados de uma pessoa cadastrada (Versão 1).
 * <p>
 * Nesta versão, o endereço é opcional.
 * </p>
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representa os dados de uma pessoa cadastrada (V1). O endereço é opcional nesta versão.")
public class PessoaDTO {

    @Schema(description = "Identificador único da pessoa", example = "1")
    private Long id;

    @Schema(description = "Nome completo da pessoa", example = "Gabriel da Silva")
    private String nome;

    @Schema(description = "Sexo da pessoa", example = "MASCULINO")
    private SexoEnum sexo;

    @Schema(description = "Endereço de e-mail válido da pessoa (opcional)", example = "gabriel@email.com")
    private String email;

    @Schema(description = "Data de nascimento no formato (YYYY-MM-DD)", example = "1990-05-10")
    private LocalDate dataNascimento;

    @Schema(description = "Cidade onde a pessoa nasceu", example = "Rio de Janeiro")
    private String naturalidade;

    @Schema(description = "Nacionalidade da pessoa", example = "Brasil")
    private String nacionalidade;

    @Schema(description = "Número do CPF da pessoa (somente números)", example = "15124127759")
    private String cpf;
}
