CREATE TABLE tb_monitor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_serie VARCHAR(255) NOT NULL UNIQUE,
    numero_patrimonio VARCHAR(255) NOT NULL UNIQUE,
    fabricante VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    data_entrada DATETIME NOT NULL,
    localizacao VARCHAR(255) NOT NULL,
    observacao TEXT,
    valor DECIMAL(19, 4),
    status VARCHAR(255) NOT NULL,
    nota_fiscal_id VARCHAR(255) NOT NULL,
    projeto_id VARCHAR(255),
    usuario_id VARCHAR(255),
    estacao_id VARCHAR(255)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4;