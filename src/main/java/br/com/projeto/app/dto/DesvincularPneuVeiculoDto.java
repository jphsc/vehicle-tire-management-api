package br.com.projeto.app.dto;

import java.io.Serializable;

public class DesvincularPneuVeiculoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idVeiculo;
	private Integer nroFogoPneu;
	
	public DesvincularPneuVeiculoDto() {
		
	}

	public Integer getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(Integer idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public Integer getNroFogoPneu() {
		return nroFogoPneu;
	}

	public void setNroFogoPneu(Integer idPneu) {
		this.nroFogoPneu = idPneu;
	}
	
}
