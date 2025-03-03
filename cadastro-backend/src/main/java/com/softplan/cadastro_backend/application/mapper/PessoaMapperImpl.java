package com.softplan.cadastro_backend.application.mapper;

import com.softplan.cadastro_backend.application.dto.PessoaDTO;
import com.softplan.cadastro_backend.application.dto.PessoaDTOV2;
import com.softplan.cadastro_backend.application.validation.PessoaValidator;
import com.softplan.cadastro_backend.domain.model.Pessoa;
import com.softplan.cadastro_backend.util.CPFUtil;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapperImpl implements PessoaMapper {

    @Override
    public PessoaDTO toDto(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }
        return PessoaDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .sexo(pessoa.getSexo())
                .email(pessoa.getEmail())
                .dataNascimento(pessoa.getDataNascimento())
                .naturalidade(pessoa.getNaturalidade())
                .nacionalidade(pessoa.getNacionalidade())
                .cpf(CPFUtil.limparCPF(pessoa.getCpf()))
                .build();
    }

    @Override
    public PessoaDTOV2 toDtoV2(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }
        return PessoaDTOV2.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .sexo(pessoa.getSexo())
                .email(pessoa.getEmail())
                .dataNascimento(pessoa.getDataNascimento())
                .naturalidade(pessoa.getNaturalidade())
                .nacionalidade(pessoa.getNacionalidade())
                .cpf(CPFUtil.limparCPF(pessoa.getCpf()))
                .endereco(pessoa.getEndereco() != null ? EnderecoMapper.toDto(pessoa.getEndereco()) : null)
                .build();
    }

    @Override
    public Pessoa toEntity(PessoaDTO pessoaDTO) {
        if (pessoaDTO == null) {
            return null;
        }
        PessoaValidator.validarPessoaDTO(pessoaDTO);
        Pessoa.PessoaBuilder builder = Pessoa.builder()
                .nome(pessoaDTO.getNome())
                .sexo(pessoaDTO.getSexo())
                .email(pessoaDTO.getEmail())
                .dataNascimento(pessoaDTO.getDataNascimento())
                .naturalidade(pessoaDTO.getNaturalidade())
                .nacionalidade(pessoaDTO.getNacionalidade())
                .cpf(CPFUtil.limparCPF(pessoaDTO.getCpf()));

        if (pessoaDTO.getId() != null) {
            builder.id(pessoaDTO.getId());
        }

        return builder.build();
    }

    @Override
    public Pessoa toEntity(PessoaDTOV2 pessoaDTOV2) {
        if (pessoaDTOV2 == null) {
            return null;
        }
        PessoaValidator.validarPessoaDTOV2(pessoaDTOV2);
        Pessoa.PessoaBuilder builder = Pessoa.builder()
                .nome(pessoaDTOV2.getNome())
                .sexo(pessoaDTOV2.getSexo())
                .email(pessoaDTOV2.getEmail())
                .dataNascimento(pessoaDTOV2.getDataNascimento())
                .naturalidade(pessoaDTOV2.getNaturalidade())
                .nacionalidade(pessoaDTOV2.getNacionalidade())
                .cpf(CPFUtil.limparCPF(pessoaDTOV2.getCpf()))
                .endereco(EnderecoMapper.toEntity(pessoaDTOV2.getEndereco()));

        if (pessoaDTOV2.getId() != null) {
            builder.id(pessoaDTOV2.getId());
        }

        return builder.build();
    }

    @Override
    public Pessoa merge(PessoaDTO dto, Pessoa existing) {
        if (existing == null) {
            throw new IllegalArgumentException("A entidade existente não pode ser nula para atualização");
        }
        if (dto == null) {
            return existing;
        }
        PessoaValidator.validarPessoaDTO(dto);
        existing.setNome(dto.getNome());
        existing.setSexo(dto.getSexo());
        existing.setEmail(dto.getEmail());
        existing.setDataNascimento(dto.getDataNascimento());
        existing.setNaturalidade(dto.getNaturalidade());
        existing.setNacionalidade(dto.getNacionalidade());
        existing.setCpf(CPFUtil.limparCPF(dto.getCpf()));
        return existing;
    }
}
