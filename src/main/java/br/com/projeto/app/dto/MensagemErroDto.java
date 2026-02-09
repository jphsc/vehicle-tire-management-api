package br.com.projeto.app.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MensagemErroDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer codigoErro;
	private String descricaoErro;
	private HttpStatus status;
	
	public MensagemErroDto() {
		
	}

	public MensagemErroDto(Integer codigoErro, String descricaoErro, HttpStatus status) {
		this.codigoErro = codigoErro;
		this.descricaoErro = descricaoErro;
		this.status = status;
	}

	public Integer getCodigoErro() {
		return codigoErro;
	}

	public void setCodigoErro(Integer codigoErro) {
		this.codigoErro = codigoErro;
	}

	public String getDescricaoErro() {
		return descricaoErro;
	}

	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}

	@JsonIgnore
	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
