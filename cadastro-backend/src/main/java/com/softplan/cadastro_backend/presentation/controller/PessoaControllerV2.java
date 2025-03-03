package com.softplan.cadastro_backend.presentation.controller;

import com.softplan.cadastro_backend.application.dto.PessoaDTOV2;
import com.softplan.cadastro_backend.application.mapper.PessoaMapper;
import com.softplan.cadastro_backend.application.service.PessoaService;
import com.softplan.cadastro_backend.domain.model.Pessoa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller responsável pela API de Pessoas na versão 2.
 * <p>
 * Nesta versão, o endereço é obrigatório.
 * </p>
 */
@RestController
@RequestMapping("/api/v2/pessoas")
@Tag(name = "Pessoa API v2", description = "Gerencia o cadastro de pessoas na versão 2 (endereço obrigatório).")
public class PessoaControllerV2 {

    private final PessoaService pessoaService;
    private final PessoaMapper pessoaMapper;

    /**
     * Construtor injetando as dependências.
     *
     * @param pessoaService serviço de pessoa.
     * @param pessoaMapper  mapper para conversão entre entidade e DTO.
     */
    public PessoaControllerV2(PessoaService pessoaService, PessoaMapper pessoaMapper) {
        this.pessoaService = pessoaService;
        this.pessoaMapper = pessoaMapper;
    }

    /**
     * Cria uma nova pessoa na versão 2, onde o endereço é obrigatório.
     *
     * @param pessoaDTOV2 Objeto DTO contendo os dados da pessoa.
     * @return A pessoa criada, no formato DTO da versão 2.
     */
    @PostMapping
    @Operation(summary = "Criar uma nova pessoa", description = "Cria uma nova pessoa na versão 2. O endereço é obrigatório.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, verifique os dados enviados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PessoaDTOV2> criarPessoa(@RequestBody PessoaDTOV2 pessoaDTOV2) {
        Pessoa pessoa = pessoaMapper.toEntity(pessoaDTOV2);
        Pessoa pessoaCriada = pessoaService.criarPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaMapper.toDtoV2(pessoaCriada));
    }

    /**
     * Busca uma pessoa pelo CPF.
     *
     * @param cpf CPF da pessoa a ser encontrada.
     * @return DTO da pessoa correspondente ao CPF informado.
     */
    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Buscar pessoa por CPF", description = "Busca uma pessoa cadastrada pelo CPF.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public ResponseEntity<PessoaDTOV2> buscarPessoaPorCpf(@PathVariable String cpf) {
        Pessoa pessoa = pessoaService.buscarPessoaPorCpf(cpf);
        return ResponseEntity.ok(pessoaMapper.toDtoV2(pessoa));
    }

    /**
     * Atualiza os dados de uma pessoa existente na versão 2.
     *
     * @param id          ID da pessoa a ser atualizada.
     * @param pessoaDTOV2 DTO contendo os dados atualizados da pessoa.
     * @return DTO da pessoa atualizada.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar pessoa", description = "Atualiza os dados de uma pessoa existente na versão 2.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, verifique os dados enviados")
    })
    @Transactional
    public ResponseEntity<PessoaDTOV2> atualizarPessoa(
            @Parameter(description = "ID da pessoa a ser atualizada", example = "1")
            @PathVariable Long id,
            @RequestBody PessoaDTOV2 pessoaDTOV2) {
        Pessoa pessoaExistente = pessoaService.buscarPessoaPorId(id);
        Pessoa pessoaAtualizada = pessoaMapper.toEntity(pessoaDTOV2);

        Pessoa pessoaSalva = pessoaService.atualizarPessoa(id, pessoaAtualizada);
        return ResponseEntity.ok(pessoaMapper.toDtoV2(pessoaSalva));
    }

    /**
     * Remove uma pessoa pelo ID.
     *
     * @param id ID da pessoa a ser removida.
     * @return Resposta sem conteúdo (HTTP 204) se a remoção for bem-sucedida.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover pessoa", description = "Remove uma pessoa pelo ID na versão 2.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pessoa removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    public ResponseEntity<Void> removerPessoa(@PathVariable Long id) {
        pessoaService.removerPessoa(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Lista todas as pessoas cadastradas na versão 2.
     *
     * @return Lista de DTOs de pessoas.
     */
    @GetMapping
    @Operation(summary = "Listar todas as pessoas", description = "Retorna uma lista com todas as pessoas cadastradas na versão 2.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de pessoas retornada com sucesso")
    })
    public ResponseEntity<List<PessoaDTOV2>> listarPessoas() {
        List<Pessoa> pessoas = pessoaService.listarTodasPessoas();
        List<PessoaDTOV2> pessoasDTO = pessoas.stream()
                .map(pessoaMapper::toDtoV2)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pessoasDTO);
    }
}
