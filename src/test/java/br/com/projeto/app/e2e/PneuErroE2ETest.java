package br.com.projeto.app.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.projeto.app.domain.enums.Enums.SituacaoVeiculo;
import br.com.projeto.app.dto.MensagemErroDto;
import br.com.projeto.app.dto.RespostaDto;
import br.com.projeto.app.dto.VeiculoDto;
import br.com.projeto.app.dto.VincularPneuVeiculoDto;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PneuErroE2ETest extends BaseE2ETest {

	@Test
	@Order(6)
    void deveRetornarErroAoVincularVeiculoInexistente() {

        VincularPneuVeiculoDto dto = new VincularPneuVeiculoDto();
        dto.setIdVeiculo(1);
        dto.setNroFogoPneu(999);
        dto.setPosicaoPneu(9);
        dto.setPressaoAtual(BigDecimal.TEN);

        ResponseEntity<MensagemErroDto> response = restTemplate
        		.exchange("/pneu/vincular", HttpMethod.PUT, new HttpEntity<>(dto), MensagemErroDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getDescricaoErro())
        .contains("Veiculo não encontrado");
    }
	
	@Test
	@Order(7)
    void deveRetornarErroAoVincularPneuInexistente() {

        VeiculoDto veiCad = new VeiculoDto();
        veiCad.setMarca("VOLVO");
        veiCad.setPlaca("ABC1234");
        veiCad.setQuilometragem(0);
        veiCad.setStatus(SituacaoVeiculo.ATIVO.getId());
        
        ResponseEntity<RespostaDto<VeiculoDto>> veiculoResp = restTemplate
        		.exchange("/veiculo", HttpMethod.POST, new HttpEntity<>(veiCad), new ParameterizedTypeReference<RespostaDto<VeiculoDto>>() {});

        VincularPneuVeiculoDto dto = new VincularPneuVeiculoDto();
        dto.setIdVeiculo(veiculoResp.getBody().getRegistros().get(0).getId());
        dto.setNroFogoPneu(999);
        dto.setPosicaoPneu(9);
        dto.setPressaoAtual(BigDecimal.TEN);
        
        ResponseEntity<MensagemErroDto> response = restTemplate
        		.exchange("/pneu/vincular", HttpMethod.PUT, new HttpEntity<>(dto), MensagemErroDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getDescricaoErro())
        .contains("Pneu não encontrado");
    }
}
