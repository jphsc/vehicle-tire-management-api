
CREATE TABLE veiculo (
    vei_id SERIAL PRIMARY KEY,
    vei_placa VARCHAR(10) NOT NULL,
    vei_modelo VARCHAR(20) NULL,
    vei_marca VARCHAR(30) NOT NULL,
    vei_quilometragem INTEGER NOT NULL,
    vei_status INTEGER NOT NULL
);

CREATE TABLE pneu (
    pneu_nro_fogo SERIAL PRIMARY KEY,
    pneu_marca VARCHAR(100) NOT NULL,
    pneu_pres_atual NUMERIC(5,2) NOT NULL,
    pneu_status INTEGER NOT NULL,
    pneu_posicao_veiculo INTEGER NULL,
    pneu_veiculo_id INTEGER
);