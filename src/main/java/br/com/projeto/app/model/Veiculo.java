package br.com.projeto.app.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import br.com.projeto.app.domain.enums.Enums.SituacaoVeiculo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@DynamicUpdate
public class Veiculo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vei_id")
	private Integer id;
	
	@Column(name = "vei_placa", nullable = false)
	private String placa;
	
	@Column(name = "vei_modelo", nullable = true)
	private String modelo;
	
	@Column(name = "vei_marca", nullable = false)
	private String marca;
	
	@Column(name = "vei_quilometragem", nullable = false)
	private Integer quilometragem;
	
	@Column(name = "vei_status", nullable = false)
	private Integer status;
	
	@OneToMany(mappedBy = "veiculo", fetch = FetchType.LAZY, orphanRemoval = false)
	private Set<Pneu> pneus = new HashSet<>();
	
	public Veiculo() {
		
	}

	public Veiculo(Integer id, String placa, String modelo, String marca, Integer quilometragem, SituacaoVeiculo status) {
		this.id = id;
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.quilometragem = quilometragem;
		this.status = status.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Integer quilometragem) {
		this.quilometragem = quilometragem;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(SituacaoVeiculo status) {
		this.status = status.getId();
	}

	public Set<Pneu> getPneus() {
		return pneus;
	}

	public void setPneus(Set<Pneu> pneus) {
		this.pneus = pneus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		return Objects.equals(id, other.id);
	}
	
}
