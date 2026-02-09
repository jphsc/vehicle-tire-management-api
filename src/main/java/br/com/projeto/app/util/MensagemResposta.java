package br.com.projeto.app.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import br.com.projeto.app.domain.exception.GlobalException;
import br.com.projeto.app.dto.MensagemErroDto;

public abstract class MensagemResposta {

	public static MensagemErroDto gerarMensagem(Exception e) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String mensagem = "";
		Integer codigoErro = 500;
		
		if(e instanceof GlobalException ex) {
			codigoErro = ex.getCodigoErro();
		} else if(e instanceof NoResourceFoundException ex) {
			codigoErro = 404;
		} else if(e instanceof HttpMessageNotReadableException ex) {
			codigoErro = 401;
		} else if (e instanceof MethodArgumentTypeMismatchException ex) {
			codigoErro = 402;
		}
		
		if(codigoErro == Constantes.COD_ERRO_INEXISTENTE) {
			status = HttpStatus.NOT_FOUND;
		} else if(codigoErro == Constantes.COD_ERRO_VALIDACAO) {
			status = HttpStatus.BAD_REQUEST;
		} else if(codigoErro == 404) {
			status = HttpStatus.NOT_FOUND;
			mensagem = Constantes.MSG_ERRO_RESOURCE_NOT_FOUND;
		} else if(codigoErro == 401) {
			status = HttpStatus.NOT_FOUND;
			mensagem = Constantes.MSG_ERRO_BODY_REQUEST;
		} else if(codigoErro == 402) {
			status = HttpStatus.BAD_REQUEST;
			mensagem = Constantes.MSG_ERRO_REQUEST_PARAM;
		} else {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mensagem = Constantes.MSG_ERRO_GERAL;
		}
		
		mensagem = mensagem.isBlank() ? e.getMessage() : mensagem;
		
		return new MensagemErroDto(codigoErro, mensagem, status);
	}

}
