package br.com.projeto.app.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.projeto.app.util.Constantes;

public class RespostaDto<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<T> registros;
	private Integer quantidade;
	private Integer pagina;
	private String mensagem;
	
	public RespostaDto() {
		
	}
	
	public static <T> RespostaDto<T> of(T registro, String mensagem, Integer pagina) {
		RespostaDto<T> dto = new RespostaDto<>();
		
		dto.registros = List.of(registro);
		dto.mensagem = mensagem;
		dto.quantidade = Constantes.NRO_MINIMO_REGISTROS;
		dto.pagina = pagina == null ? 1 : pagina;
		
		return dto;
	}
	
	public static <T> RespostaDto<T> of(Page<T> page, String mensagem) {
		RespostaDto<T> dto = new RespostaDto<>();
		
		dto.registros = page.getContent();
		dto.mensagem = mensagem;
		dto.quantidade = page.getNumberOfElements();
		dto.pagina = page.getNumber() + 1;
		
		return dto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	public List<T> getRegistros() {
		return registros;
	}

	public void setRegistros(List<T> registros) {
		this.registros = registros;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
