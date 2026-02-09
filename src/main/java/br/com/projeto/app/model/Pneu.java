package br.com.projeto.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.hibernate.annotations.DynamicUpdate;

import br.com.projeto.app.domain.enums.Enums.PosicoesPneu;
import br.com.projeto.app.domain.enums.Enums.StatusPneu;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@DynamicUpdate
public class Pneu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pneu_nro_fogo", nullable = false, updatable = false)
	private Integer numeroFogo;

	@Column(name = "pneu_marca", nullable = false, updatable = false)
	private String marca;

	@Column(name = "pneu_pres_atual", nullable = false, updatable = true, precision = 5, scale = 2)
	private BigDecimal pressaoAtual;

	@Column(name = "pneu_status", nullable = false, updatable = true)
	private Integer status;
	
	@Column(name = "pneu_posicao_veiculo", nullable = true, updatable = true)
	private Integer posicaoPneu;
	
	@ManyToOne
	@JoinColumn(name = "pneu_veiculo_id", nullable = true, updatable = true)
	private Veiculo veiculo;
	
	public Pneu() {
		
	}

	public Pneu(Integer numeroFogo, String marca, BigDecimal pressaoAtual, StatusPneu status,
			PosicoesPneu posicaoPneu, Veiculo veiculo) {
		this.numeroFogo = numeroFogo;
		this.marca = marca;
		this.pressaoAtual = pressaoAtual;
		this.status = status.getId();
		this.posicaoPneu = posicaoPneu.getId();
		this.veiculo = veiculo;
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

	public void setStatus(StatusPneu status) {
		this.status = status.getId();
	}

	public Integer getPosicaoPneu() {
		return posicaoPneu;
	}

	public void setPosicaoPneu(PosicoesPneu posicaoPneu) {
		this.posicaoPneu = (posicaoPneu == null ? null : posicaoPneu.getId());
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroFogo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pneu other = (Pneu) obj;
		return Objects.equals(numeroFogo, other.numeroFogo);
	}

	@Override
	public String toString() {
		return "Pneu [numeroFogo=" + numeroFogo + ", marca=" + marca + ", pressaoAtual=" + pressaoAtual + ", status="
				+ status + ", posicaoPneu=" + posicaoPneu + ", veiculo=" + veiculo + "]";
	}
	
}
