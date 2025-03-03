package com.softplan.cadastro_backend.domain.repository;

import com.softplan.cadastro_backend.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositório para operações de banco de dados relacionadas à entidade {@link Pessoa}.
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    /**
     * Busca uma pessoa pelo CPF.
     *
     * @param cpf CPF da pessoa a ser encontrada.
     * @return {@link Optional} contendo a pessoa, se encontrada, ou vazio caso contrário.
     */
    Optional<Pessoa> findByCpf(String cpf);
}
