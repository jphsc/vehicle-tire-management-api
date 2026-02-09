package br.com.projeto.app.domain.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import br.com.projeto.app.dto.MensagemErroDto;
import br.com.projeto.app.util.MensagemResposta;

@ControllerAdvice
public class GlobalExceptionMapper {

	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<MensagemErroDto> erroGlobal(GlobalException e){
		
		MensagemErroDto erro = MensagemResposta.gerarMensagem(e);
		return ResponseEntity.status(erro.getStatus()).body(erro);
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<MensagemErroDto> erroResource(NoResourceFoundException e){
		
		MensagemErroDto erro = MensagemResposta.gerarMensagem(e);
		return ResponseEntity.status(erro.getStatus()).body(erro);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<MensagemErroDto> erroJsonRequest(HttpMessageNotReadableException e){
		
		MensagemErroDto erro = MensagemResposta.gerarMensagem(e);
		return ResponseEntity.status(erro.getStatus()).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<MensagemErroDto> erroQueryParamReq(MethodArgumentTypeMismatchException e){
		
		MensagemErroDto erro = MensagemResposta.gerarMensagem(e);
		return ResponseEntity.status(erro.getStatus()).body(erro);
	}
}


