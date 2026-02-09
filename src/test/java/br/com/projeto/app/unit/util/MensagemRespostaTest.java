package br.com.projeto.app.unit.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import br.com.projeto.app.domain.exception.GlobalException;
import br.com.projeto.app.dto.MensagemErroDto;
import br.com.projeto.app.util.Constantes;
import br.com.projeto.app.util.MensagemResposta;

public class MensagemRespostaTest {

	@Test
    void deveRetornarStatus404QuandoErroInexistente() {

        GlobalException ex = new GlobalException(Constantes.COD_ERRO_INEXISTENTE, Constantes.MSG_REGISTROS_NAO_ENCONTRADOS);
        MensagemErroDto erro = MensagemResposta.gerarMensagem(ex);

        assertThat(erro.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deveRetornarStatus400QuandoErroValidacao() {

        GlobalException ex =  new GlobalException(Constantes.COD_ERRO_VALIDACAO, Constantes.MSG_ERRO_CAMPO_OBRIGATORIO_FALTANTE);
        MensagemErroDto erro = MensagemResposta.gerarMensagem(ex);

        assertThat(erro.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
