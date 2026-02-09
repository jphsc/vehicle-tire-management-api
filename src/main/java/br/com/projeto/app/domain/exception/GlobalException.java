package br.com.projeto.app.domain.exception;

import br.com.projeto.app.util.Constantes;

public class GlobalException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Integer codigoErro;
	private String descricaoErro;
	
	public GlobalException(String descricaoErro) {
		super(descricaoErro);
		this.codigoErro = Constantes.COD_ERRO_SERVIDOR_INTERNO;
		this.descricaoErro = descricaoErro;
	}

	public GlobalException(Integer codigoErro, String descricaoErro) {
		super(descricaoErro);
		this.codigoErro = codigoErro;
		this.descricaoErro = descricaoErro;
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
}
