CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    pessoa_id INTEGER NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa (id) ON DELETE CASCADE
);

ALTER TABLE pessoa ADD COLUMN endereco_id INTEGER;
ALTER TABLE pessoa ADD CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) REFERENCES endereco (id);

COMMENT ON COLUMN endereco.id IS 'Identificador único do endereço.';
COMMENT ON COLUMN endereco.pessoa_id IS 'ID da pessoa associada ao endereço. Cada pessoa pode ter no máximo um endereço.';
COMMENT ON COLUMN endereco.logradouro IS 'Nome da rua, avenida, rodovia, etc.';
COMMENT ON COLUMN endereco.numero IS 'Número do imóvel no endereço. Pode conter valores alfanuméricos.';
COMMENT ON COLUMN endereco.complemento IS 'Informações adicionais ao endereço, como bloco, apartamento, etc.';
COMMENT ON COLUMN endereco.bairro IS 'Nome do bairro onde a pessoa reside.';
COMMENT ON COLUMN endereco.cidade IS 'Cidade onde a pessoa reside.';
COMMENT ON COLUMN endereco.estado IS 'Estado onde a pessoa reside.';
COMMENT ON COLUMN endereco.cep IS 'Código de Endereçamento Postal (CEP) do endereço.';
COMMENT ON COLUMN endereco.data_cadastro IS 'Data e hora do cadastro do endereço.';
COMMENT ON COLUMN endereco.data_atualizacao IS 'Data e hora da última atualização do endereço.';

COMMENT ON COLUMN pessoa.endereco_id IS 'ID do endereço associado à pessoa. Relacionamento 1-1 com a tabela endereco.';
