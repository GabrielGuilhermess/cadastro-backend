package com.softplan.cadastro_backend.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softplan.cadastro_backend.application.dto.PessoaDTO;
import com.softplan.cadastro_backend.application.mapper.PessoaMapper;
import com.softplan.cadastro_backend.application.service.PessoaService;
import com.softplan.cadastro_backend.domain.model.Pessoa;
import com.softplan.cadastro_backend.presentation.exception.GlobalExceptionHandler;
import com.softplan.cadastro_backend.presentation.exception.PessoaNaoEncontradaException;
import com.softplan.cadastro_backend.utils.ConstantsTestData;
import com.softplan.cadastro_backend.utils.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testes unitários para o controller {@link PessoaController} (versão 1).
 * <p>
 * Nesta versão, o cadastro de pessoas não contempla o endereço, ou seja, o DTO não possui esse campo.
 * </p>
 */
@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private PessoaService pessoaService;

    @Mock
    private PessoaMapper pessoaMapper;

    private PessoaDTO pessoaDTO;
    private Pessoa pessoaValida;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        pessoaDTO = TestDataFactory.criarPessoaDTO();
        pessoaValida = TestDataFactory.criarPessoaValida();
    }

    /**
     * Testa a criação de uma nova pessoa.
     *
     * @throws Exception em caso de erro na execução do teste.
     */
    @Test
    public void testCriarPessoa() throws Exception {
        when(pessoaMapper.toEntity(any(PessoaDTO.class))).thenReturn(pessoaValida);
        when(pessoaService.criarPessoa(any(Pessoa.class))).thenReturn(pessoaValida);
        when(pessoaMapper.toDto(any(Pessoa.class))).thenReturn(pessoaDTO);

        mockMvc.perform(post("/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pessoaDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(ConstantsTestData.NOME_VALIDO));
    }

    /**
     * Testa a busca de uma pessoa por CPF com sucesso.
     *
     * @throws Exception em caso de erro na execução do teste.
     */
    @Test
    public void testBuscarPessoaPorCpf_Sucesso() throws Exception {
        when(pessoaService.buscarPessoaPorCpf(ConstantsTestData.CPF_VALIDO)).thenReturn(pessoaValida);
        when(pessoaMapper.toDto(any(Pessoa.class))).thenReturn(pessoaDTO);

        mockMvc.perform(get("/pessoas/cpf/{cpf}", ConstantsTestData.CPF_VALIDO)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(ConstantsTestData.NOME_VALIDO));
    }

    /**
     * Testa a atualização de uma pessoa com sucesso.
     * <p>
     * Nesta versão, o endereço não é modificado, sendo preservado da entidade existente.
     * </p>
     *
     * @throws Exception em caso de erro na execução do teste.
     */
    @Test
    public void testAtualizarPessoa_Sucesso() throws Exception {
        PessoaDTO dtoAtualizado = TestDataFactory.criarPessoaDTO();
        dtoAtualizado.setNome("Novo Nome");

        when(pessoaService.buscarPessoaPorId(anyLong())).thenReturn(pessoaValida);
        when(pessoaMapper.toEntity(any(PessoaDTO.class))).thenReturn(pessoaValida);
        when(pessoaService.atualizarPessoa(anyLong(), any(Pessoa.class)))
                .thenAnswer(invocation -> {
                    Pessoa input = invocation.getArgument(1);
                    input.setId(ConstantsTestData.ID_VALIDO);
                    return input;
                });
        when(pessoaMapper.toDto(any(Pessoa.class))).thenReturn(dtoAtualizado);

        mockMvc.perform(put("/pessoas/{id}", ConstantsTestData.ID_VALIDO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Novo Nome"));
    }

    /**
     * Testa a atualização de uma pessoa que não é encontrada.
     *
     * @throws Exception em caso de erro na execução do teste.
     */
    @Test
    public void testAtualizarPessoa_NaoEncontrada() throws Exception {
        PessoaDTO dtoAtualizado = TestDataFactory.criarPessoaDTO();

        when(pessoaService.buscarPessoaPorId(anyLong()))
                .thenThrow(new PessoaNaoEncontradaException(ConstantsTestData.ID_VALIDO));

        mockMvc.perform(put("/pessoas/{id}", ConstantsTestData.ID_VALIDO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoAtualizado)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Pessoa com ID " + ConstantsTestData.ID_VALIDO + " não encontrada."));
    }

    /**
     * Testa a remoção de uma pessoa com sucesso.
     *
     * @throws Exception em caso de erro na execução do teste.
     */
    @Test
    public void testRemoverPessoa_Sucesso() throws Exception {
        doNothing().when(pessoaService).removerPessoa(ConstantsTestData.ID_VALIDO);

        mockMvc.perform(delete("/pessoas/{id}", ConstantsTestData.ID_VALIDO))
                .andExpect(status().isNoContent());

        verify(pessoaService, times(1)).removerPessoa(ConstantsTestData.ID_VALIDO);
    }

    /**
     * Testa a remoção de uma pessoa que não é encontrada.
     *
     * @throws Exception em caso de erro na execução do teste.
     */
    @Test
    public void testRemoverPessoa_NaoEncontrada() throws Exception {
        doThrow(new PessoaNaoEncontradaException(ConstantsTestData.ID_VALIDO))
                .when(pessoaService).removerPessoa(ConstantsTestData.ID_VALIDO);

        mockMvc.perform(delete("/pessoas/{id}", ConstantsTestData.ID_VALIDO))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Pessoa com ID " + ConstantsTestData.ID_VALIDO + " não encontrada."));
    }

    /**
     * Testa a listagem de todas as pessoas cadastradas.
     *
     * @throws Exception em caso de erro na execução do teste.
     */
    @Test
    public void testListarPessoas() throws Exception {
        Pessoa pessoa1 = TestDataFactory.criarPessoaValida();
        Pessoa pessoa2 = TestDataFactory.criarPessoaAlternativa();

        when(pessoaService.listarTodasPessoas()).thenReturn(List.of(pessoa1, pessoa2));
        when(pessoaMapper.toDto(pessoa1)).thenReturn(TestDataFactory.criarPessoaDTO());
        when(pessoaMapper.toDto(pessoa2)).thenReturn(TestDataFactory.criarPessoaDTOAlternativo());

        mockMvc.perform(get("/pessoas")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value(ConstantsTestData.NOME_VALIDO))
                .andExpect(jsonPath("$[1].nome").value(ConstantsTestData.NOME_ALTERNATIVO));
    }
}
