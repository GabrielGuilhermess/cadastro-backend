ALTER TABLE pessoa ADD COLUMN logradouro VARCHAR(255);
ALTER TABLE pessoa ADD COLUMN numero VARCHAR(20);
ALTER TABLE pessoa ADD COLUMN complemento VARCHAR(100);
ALTER TABLE pessoa ADD COLUMN bairro VARCHAR(100);
ALTER TABLE pessoa ADD COLUMN cidade VARCHAR(100);
ALTER TABLE pessoa ADD COLUMN estado VARCHAR(50);
ALTER TABLE pessoa ADD COLUMN cep VARCHAR(10);

COMMENT ON COLUMN pessoa.logradouro IS 'Nome da rua, avenida, rodovia, etc.';
COMMENT ON COLUMN pessoa.numero IS 'Número do imóvel no endereço. Pode conter valores alfanuméricos.';
COMMENT ON COLUMN pessoa.complemento IS 'Informações adicionais ao endereço, como bloco, apartamento, etc.';
COMMENT ON COLUMN pessoa.bairro IS 'Nome do bairro onde a pessoa reside.';
COMMENT ON COLUMN pessoa.cidade IS 'Cidade onde a pessoa reside.';
COMMENT ON COLUMN pessoa.estado IS 'Estado onde a pessoa reside.';
COMMENT ON COLUMN pessoa.cep IS 'Código de Endereçamento Postal (CEP) do endereço.';
