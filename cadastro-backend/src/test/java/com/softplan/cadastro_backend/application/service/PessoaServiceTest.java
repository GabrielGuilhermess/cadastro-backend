package com.softplan.cadastro_backend.application.service;

import com.softplan.cadastro_backend.domain.model.Pessoa;
import com.softplan.cadastro_backend.domain.repository.PessoaRepository;
import com.softplan.cadastro_backend.presentation.exception.PessoaNaoEncontradaException;
import com.softplan.cadastro_backend.utils.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para a classe {@link PessoaService}.
 */
@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    /**
     * Configuração do ambiente de teste.
     */
    @BeforeEach
    public void setup() {
    }

    /**
     * Testa a criação de uma pessoa válida.
     */
    @Test
    public void testCriarPessoaValida() {
        Pessoa pessoa = TestDataFactory.criarPessoaComEndereco();

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa pessoaCriada = pessoaService.criarPessoa(pessoa);

        assertNotNull(pessoaCriada, "Pessoa criada não deve ser nula");
        verify(pessoaRepository, times(1)).save(pessoa);
    }

    /**
     * Testa a busca de uma pessoa pelo CPF quando ela é encontrada.
     */
    @Test
    public void testBuscarPessoaPorCpfEncontrada() {
        Pessoa pessoa = TestDataFactory.criarPessoaComEndereco();

        when(pessoaRepository.findByCpf(pessoa.getCpf())).thenReturn(Optional.of(pessoa));

        Pessoa pessoaEncontrada = pessoaService.buscarPessoaPorCpf(pessoa.getCpf());

        assertNotNull(pessoaEncontrada, "A pessoa deve ser encontrada pelo CPF");
        assertEquals(pessoa.getCpf(), pessoaEncontrada.getCpf(), "CPF da pessoa encontrada não confere");
        verify(pessoaRepository, times(1)).findByCpf(pessoa.getCpf());
    }

    /**
     * Testa a busca de uma pessoa pelo CPF quando ela não é encontrada.
     */
    @Test
    public void testBuscarPessoaPorCpfNaoEncontrada() {
        String cpfNaoExistente = "00000000000";

        when(pessoaRepository.findByCpf(cpfNaoExistente)).thenReturn(Optional.empty());

        PessoaNaoEncontradaException exception = assertThrows(PessoaNaoEncontradaException.class,
                () -> pessoaService.buscarPessoaPorCpf(cpfNaoExistente));

        assertEquals("Pessoa com CPF " + cpfNaoExistente + " não encontrada.", exception.getMessage());
        verify(pessoaRepository, times(1)).findByCpf(cpfNaoExistente);
    }

    /**
     * Testa a listagem de todas as pessoas.
     */
    @Test
    public void testListarTodasPessoas() {
        when(pessoaRepository.findAll()).thenReturn(
                List.of(TestDataFactory.criarPessoaComEndereco(), TestDataFactory.criarPessoaSemEndereco()));

        List<Pessoa> pessoas = pessoaService.listarTodasPessoas();

        assertNotNull(pessoas, "A lista de pessoas não deve ser nula");
        assertEquals(2, pessoas.size(), "Deve haver 2 pessoas na lista");
        verify(pessoaRepository, times(1)).findAll();
    }

    /**
     * Testa a atualização de uma pessoa existente.
     */
    @Test
    public void testAtualizarPessoaComSucesso() {
        Pessoa pessoaExistente = TestDataFactory.criarPessoaComEndereco();
        Pessoa pessoaAtualizada = TestDataFactory.criarPessoaAtualizada();

        when(pessoaRepository.findById(pessoaExistente.getId())).thenReturn(Optional.of(pessoaExistente));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaAtualizada);

        Pessoa resultado = pessoaService.atualizarPessoa(pessoaExistente.getId(), pessoaAtualizada);

        assertNotNull(resultado, "A pessoa atualizada não deve ser nula");
        assertEquals(pessoaAtualizada.getNome(), resultado.getNome(), "O nome deve ser atualizado");
        verify(pessoaRepository, times(1)).findById(pessoaExistente.getId());
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    /**
     * Testa a tentativa de atualização de uma pessoa inexistente.
     */
    @Test
    public void testAtualizarPessoaNaoEncontrada() {
        Long idNaoExistente = -999L;
        Pessoa pessoa = TestDataFactory.criarPessoaComEndereco();

        when(pessoaRepository.findById(idNaoExistente)).thenReturn(Optional.empty());

        PessoaNaoEncontradaException exception = assertThrows(PessoaNaoEncontradaException.class,
                () -> pessoaService.atualizarPessoa(idNaoExistente, pessoa));

        assertEquals("Pessoa com ID " + idNaoExistente + " não encontrada.", exception.getMessage());
        verify(pessoaRepository, times(1)).findById(idNaoExistente);
    }

    /**
     * Testa a remoção de uma pessoa existente.
     */
    @Test
    public void testRemoverPessoaComSucesso() {
        Long idValido = 1L;

        when(pessoaRepository.existsById(idValido)).thenReturn(true);
        doNothing().when(pessoaRepository).deleteById(idValido);

        pessoaService.removerPessoa(idValido);

        verify(pessoaRepository, times(1)).deleteById(idValido);
    }

    /**
     * Testa a tentativa de remoção de uma pessoa inexistente.
     */
    @Test
    public void testRemoverPessoaNaoExistente() {
        Long idInexistente = -999L;
        when(pessoaRepository.existsById(idInexistente)).thenReturn(false);

        PessoaNaoEncontradaException exception = assertThrows(PessoaNaoEncontradaException.class,
                () -> pessoaService.removerPessoa(idInexistente));

        assertEquals("Pessoa com ID " + idInexistente + " não encontrada.", exception.getMessage());
        verify(pessoaRepository, never()).deleteById(any());
    }
}
