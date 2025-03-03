package com.softplan.cadastro_backend.presentation.controller;

import com.softplan.cadastro_backend.application.dto.PessoaDTO;
import com.softplan.cadastro_backend.application.mapper.PessoaMapper;
import com.softplan.cadastro_backend.application.service.PessoaService;
import com.softplan.cadastro_backend.domain.model.Pessoa;
import com.softplan.cadastro_backend.util.CPFUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pela API de Pessoas na versão 1.
 * <p>
 * Nesta versão, o cadastro de pessoas não contempla o endereço.
 * </p>
 */
@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
@Tag(name = "Pessoa API v1", description = "Gerencia o cadastro de pessoas sem informações de endereço")
public class PessoaController {

    private final PessoaService pessoaService;
    private final PessoaMapper pessoaMapper;

    /**
     * Cria uma nova pessoa no sistema.
     *
     * @param pessoaDTO DTO contendo os dados da pessoa.
     * @return DTO da pessoa criada.
     */
    @PostMapping
    @Operation(summary = "Criar uma nova pessoa", description = "Cadastra uma nova pessoa na versão 1 (sem endereço).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados")
    })
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);

        if (pessoaService.existePorCpf(pessoa.getCpf())) {
            throw new IllegalArgumentException("Já existe uma pessoa com esse CPF");
        }

        if (pessoaDTO.getId() != null) {
            throw new IllegalArgumentException("Não informe o id para o cadastro de pessoa");
        }

        pessoa.setId(null);
        Pessoa pessoaCriada = pessoaService.criarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaMapper.toDto(pessoaCriada));
    }

    /**
     * Busca uma pessoa pelo CPF.
     *
     * @param cpf CPF da pessoa a ser buscada.
     * @return DTO da pessoa encontrada.
     */
    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Buscar pessoa pelo CPF", description = "Busca uma pessoa cadastrada pelo CPF.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public ResponseEntity<PessoaDTO> buscarPessoaPorCpf(
            @Parameter(description = "CPF da pessoa", example = "123.456.789-00")
            @PathVariable String cpf) {
        Pessoa pessoa = pessoaService.buscarPessoaPorCpf(CPFUtil.limparCPF(cpf));
        return ResponseEntity.ok(pessoaMapper.toDto(pessoa));
    }

    /**
     * Atualiza os dados de uma pessoa pelo ID.
     * <p>
     * O endereço não será modificado na versão 1.
     * </p>
     *
     * @param id        ID da pessoa a ser atualizada.
     * @param pessoaDTO DTO contendo os dados atualizados.
     * @return DTO da pessoa atualizada.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pessoa", description = "Atualiza os dados de uma pessoa na versão 1 (sem modificar endereço).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @Transactional
    public ResponseEntity<PessoaDTO> atualizarPessoa(
            @Parameter(description = "ID da pessoa a ser atualizada", example = "1")
            @PathVariable Long id,
            @RequestBody PessoaDTO pessoaDTO) {

        Pessoa pessoaExistente = pessoaService.buscarPessoaPorId(id);
        Pessoa pessoaAtualizada = pessoaMapper.toEntity(pessoaDTO);
        pessoaAtualizada.setEndereco(pessoaExistente.getEndereco());

        Pessoa pessoaSalva = pessoaService.atualizarPessoa(id, pessoaAtualizada);
        return ResponseEntity.ok(pessoaMapper.toDto(pessoaSalva));
    }

    /**
     * Remove uma pessoa pelo ID.
     *
     * @param id ID da pessoa a ser removida.
     * @return Resposta sem conteúdo se a remoção for bem-sucedida.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover pessoa", description = "Exclui uma pessoa do sistema pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public ResponseEntity<Void> removerPessoa(
            @Parameter(description = "ID da pessoa a ser removida", example = "1")
            @PathVariable Long id) {
        pessoaService.removerPessoa(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Lista todas as pessoas cadastradas.
     *
     * @return Lista de DTOs de pessoas.
     */
    @GetMapping
    @Operation(summary = "Listar todas as pessoas", description = "Retorna uma lista de todas as pessoas cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pessoas retornada com sucesso")
    })
    public ResponseEntity<List<PessoaDTO>> listarPessoas() {
        List<Pessoa> pessoas = pessoaService.listarTodasPessoas();
        List<PessoaDTO> pessoasDTO = pessoas.stream().map(pessoaMapper::toDto).toList();
        return ResponseEntity.ok(pessoasDTO);
    }
}
