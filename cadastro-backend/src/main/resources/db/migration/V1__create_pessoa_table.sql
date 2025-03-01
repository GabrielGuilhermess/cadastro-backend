CREATE TABLE pessoa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cod_sexo INTEGER NOT NULL DEFAULT 0,
    email VARCHAR(255),
    data_nascimento DATE NOT NULL,
    naturalidade VARCHAR(100),
    nacionalidade VARCHAR(100),
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

COMMENT ON COLUMN pessoa.id IS 'Identificador único da pessoa';
COMMENT ON COLUMN pessoa.nome IS 'Nome completo da pessoa';
COMMENT ON COLUMN pessoa.cod_sexo IS 'Código do sexo (0 = Indefinido, 1 = Masculino, 2 = Feminino, 3 = Outro)';
COMMENT ON COLUMN pessoa.email IS 'E-mail da pessoa (opcional, deve ser validado se preenchido)';
COMMENT ON COLUMN pessoa.data_nascimento IS 'Data de nascimento da pessoa';
COMMENT ON COLUMN pessoa.naturalidade IS 'Cidade/Estado onde a pessoa nasceu';
COMMENT ON COLUMN pessoa.nacionalidade IS 'Nacionalidade da pessoa';
COMMENT ON COLUMN pessoa.cpf IS 'CPF da pessoa, deve ser único e validado';
COMMENT ON COLUMN pessoa.data_cadastro IS 'Data e hora do cadastro';
COMMENT ON COLUMN pessoa.data_atualizacao IS 'Data e hora da última atualização';
