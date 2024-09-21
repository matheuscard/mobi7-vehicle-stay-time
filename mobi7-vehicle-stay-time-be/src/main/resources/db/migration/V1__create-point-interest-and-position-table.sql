CREATE TABLE ponto_interesse (
	id BIGINT(19) NOT NULL PRIMARY KEY,
	latitude DOUBLE NOT NULL,
	longitude DOUBLE NOT NULL,
	nome VARCHAR(100) NOT NULL,
	raio INT(10) NOT NULL
);
CREATE TABLE posicao (
	id BIGINT(19) NOT NULL PRIMARY KEY,
	data DATETIME(6) NULL DEFAULT NULL,
	latitude DOUBLE NULL DEFAULT NULL,
	longitude DOUBLE NULL DEFAULT NULL,
	ponto_interesse_id BIGINT(19) NULL DEFAULT NULL,
	ignicao BIT(1) NULL DEFAULT NULL,
	placa VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	velocidade INT(10) NULL DEFAULT NULL,
	INDEX `fk_ponto_interesse_id` (ponto_interesse_id) USING BTREE,
	CONSTRAINT `fk_ponto_interesse_id` FOREIGN KEY (ponto_interesse_id) REFERENCES ponto_interesse (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);