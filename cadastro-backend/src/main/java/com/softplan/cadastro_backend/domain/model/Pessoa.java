package com.softplan.cadastro_backend.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.softplan.cadastro_backend.domain.enums.SexoEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Representa uma pessoa no sistema.
 * <p>
 * Essa classe é mapeada como uma entidade no banco de dados e contém informações
 * básicas sobre a pessoa, incluindo dados pessoais e o endereço embutido.
 * </p>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Schema(description = "Modelo de Pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(description = "Identificador único da pessoa", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome da pessoa", example = "Gabriel da Silva")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Sexo da pessoa", example = "MASCULINO")
    private SexoEnum sexo;

    @Schema(description = "E-mail válido (opcional)", example = "gabriel@email.com")
    private String email;

    @Schema(description = "Data de nascimento (YYYY-MM-DD)", example = "1990-05-10")
    private LocalDate dataNascimento;

    @Schema(description = "Naturalidade da pessoa", example = "Rio de Janeiro")
    private String naturalidade;

    @Schema(description = "Nacionalidade da pessoa", example = "Brasil")
    private String nacionalidade;

    @Column(unique = true, nullable = false)
    @Schema(description = "CPF da pessoa", example = "15124127759")
    private String cpf;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @Embedded
    private Endereco endereco;

    /**
     * Método de callback que é executado antes da persistência da entidade.
     * <p>
     * Define os timestamps de cadastro e atualização.
     * </p>
     */
    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    /**
     * Método de callback que é executado antes da atualização da entidade.
     * <p>
     * Atualiza o timestamp de atualização.
     * </p>
     */
    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
