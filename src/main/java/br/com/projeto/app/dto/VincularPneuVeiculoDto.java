package br.com.projeto.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class VincularPneuVeiculoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idVeiculo;
	private Integer nroFogoPneu;
	private Integer posicaoPneu;
	private BigDecimal pressaoAtual;
	
	public VincularPneuVeiculoDto() {
		
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

	public Integer getPosicaoPneu() {
		return posicaoPneu;
	}

	public void setPosicaoPneu(Integer posicaoPneu) {
		this.posicaoPneu = posicaoPneu;
	}

	public BigDecimal getPressaoAtual() {
		return pressaoAtual;
	}

	public void setPressaoAtual(BigDecimal pressaoAtual) {
		this.pressaoAtual = pressaoAtual;
	}
	
}
