CREATE TABLE ponto_interesse(
    id BIGINT NOT NULL PRIMARY KEY,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    nome VARCHAR(100) NOT NULL,
    raio INTEGER NOT NULL
);
CREATE TABLE posicao(
    id BIGINT NOT NULL PRIMARY KEY,
    DATA TIMESTAMP NULL DEFAULT NULL,
    latitude DOUBLE PRECISION NULL DEFAULT NULL,
    longitude DOUBLE PRECISION NULL DEFAULT NULL,
    ponto_interesse_id BIGINT,
    ignicao BOOLEAN NULL DEFAULT NULL,
    placa VARCHAR(255) NULL DEFAULT NULL,
    velocidade INTEGER  NULL DEFAULT NULL,
    CONSTRAINT fk_ponto_interesse_id FOREIGN KEY(ponto_interesse_id) REFERENCES ponto_interesse(id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE INDEX fk_ponto_interesse_id ON posicao(ponto_interesse_id);