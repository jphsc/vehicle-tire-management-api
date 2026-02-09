package br.com.projeto.app.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PneuDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer numeroFogo;
	private String marca;
	private BigDecimal pressaoAtual;
	private Integer status;
	private Integer posicaoPneu;
	
	public PneuDto() {
		
	}
	
	public PneuDto(Integer numeroFogo, String marca, BigDecimal pressaoAtual, Integer status, Integer posicaoPneu) {
		this.numeroFogo = numeroFogo;
		this.marca = marca;
		this.pressaoAtual = pressaoAtual;
		this.status = status;
		this.posicaoPneu = posicaoPneu;
	}


	public Integer getNumeroFogo() {
		return numeroFogo;
	}

	public void setNumeroFogo(Integer numeroFogo) {
		this.numeroFogo = numeroFogo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public BigDecimal getPressaoAtual() {
		return pressaoAtual;
	}

	public void setPressaoAtual(BigDecimal pressaoAtual) {
		this.pressaoAtual = pressaoAtual;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPosicaoPneu() {
		return posicaoPneu;
	}

	public void setPosicaoPneu(Integer posicaoPneu) {
		this.posicaoPneu = posicaoPneu;
	}

	@Override
	public String toString() {
		return "PneuDto [numeroFogo=" + numeroFogo + ", marca=" + marca + ", pressaoAtual=" + pressaoAtual + ", status="
				+ status + ", posicaoPneu=" + posicaoPneu + "]";
	}
}
