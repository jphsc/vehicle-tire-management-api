package br.com.projeto.app.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

public class VeiculoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String placa;
	private String modelo;
	private String marca;
	private Integer quilometragem;
	private Integer status;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Set<PneuDto> pneus = new HashSet<>();
	
	public VeiculoDto() {
		
	}
	
	public VeiculoDto(Integer id, String placa, String modelo, String marca, Integer quilometragem, Integer status,
			Set<PneuDto> pneus) {
		this.id = id;
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.quilometragem = quilometragem;
		this.status = status;
		this.pneus = pneus;
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

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<PneuDto> getPneus() {
		return pneus;
	}

	public void setPneus(Set<PneuDto> pneus) {
		this.pneus = pneus;
	}
	
	@Override
	public String toString() {
		return "VeiculoDto [id=" + id + ", placa=" + placa + ", modelo=" + modelo + ", marca=" + marca
				+ ", quilometragem=" + quilometragem + ", status=" + status + ", pneus=" + pneus + "]";
	}
	
}
