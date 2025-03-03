package com.softplan.cadastro_backend.application.service;

import com.softplan.cadastro_backend.domain.model.Pessoa;
import com.softplan.cadastro_backend.domain.repository.PessoaRepository;
import com.softplan.cadastro_backend.presentation.exception.PessoaNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço responsável pelas operações de gerenciamento de pessoas.
 * <p>
 * Fornece métodos para criação, atualização, busca e remoção de pessoas.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    /**
     * Busca uma pessoa pelo ID.
     *
     * @param id Identificador da pessoa.
     * @return A pessoa encontrada.
     * @throws PessoaNaoEncontradaException se a pessoa não for encontrada.
     */
    public Pessoa buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNaoEncontradaException(id));
    }

    /**
     * Cria uma nova pessoa no sistema.
     *
     * @param pessoa A pessoa a ser criada.
     * @return A pessoa criada.
     */
    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    /**
     * Busca uma pessoa pelo CPF.
     *
     * @param cpf CPF da pessoa.
     * @return A pessoa encontrada.
     * @throws PessoaNaoEncontradaException se a pessoa não for encontrada.
     */
    public Pessoa buscarPessoaPorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf)
                .orElseThrow(() -> new PessoaNaoEncontradaException(cpf));
    }

    /**
     * Verifica se já existe uma pessoa cadastrada com o CPF informado.
     *
     * @param cpf CPF a ser verificado.
     * @return true se já existir uma pessoa com esse CPF; false caso contrário.
     */
    public boolean existePorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf).isPresent();
    }

    /**
     * Atualiza os dados de uma pessoa existente.
     *
     * @param id               Identificador da pessoa a ser atualizada.
     * @param pessoaAtualizada Objeto contendo os dados atualizados.
     * @return A pessoa atualizada.
     * @throws PessoaNaoEncontradaException se a pessoa não for encontrada.
     */
    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = buscarPessoaPorId(id);

        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setSexo(pessoaAtualizada.getSexo());
        pessoaExistente.setEmail(pessoaAtualizada.getEmail());
        pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoaExistente.setNaturalidade(pessoaAtualizada.getNaturalidade());
        pessoaExistente.setNacionalidade(pessoaAtualizada.getNacionalidade());
        pessoaExistente.setCpf(pessoaAtualizada.getCpf());

        if (pessoaAtualizada.getEndereco() != null) {
            pessoaExistente.setEndereco(pessoaAtualizada.getEndereco());
        }

        return pessoaRepository.save(pessoaExistente);
    }

    /**
     * Remove uma pessoa pelo ID.
     *
     * @param id Identificador da pessoa a ser removida.
     * @throws PessoaNaoEncontradaException se a pessoa não for encontrada.
     */
    @Transactional
    public void removerPessoa(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new PessoaNaoEncontradaException(id);
        }
        pessoaRepository.deleteById(id);
    }

    /**
     * Lista todas as pessoas cadastradas.
     *
     * @return Lista de todas as pessoas.
     */
    public List<Pessoa> listarTodasPessoas() {
        return pessoaRepository.findAll();
    }
}
