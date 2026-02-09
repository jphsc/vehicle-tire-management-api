
ALTER TABLE pneu 
ADD CONSTRAINT fk_pneu_veiculo_01
FOREIGN KEY (pneu_veiculo_id)
REFERENCES veiculo(vei_id);