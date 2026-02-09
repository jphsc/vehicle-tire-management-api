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
import org.springframework.http.ResponseEntity;

import br.com.projeto.app.dto.DesvincularPneuVeiculoDto;
import br.com.projeto.app.dto.PneuDto;
import br.com.projeto.app.dto.RespostaDto;
import br.com.projeto.app.dto.VeiculoDto;
import br.com.projeto.app.dto.VincularPneuVeiculoDto;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PneuE2ETest extends BaseE2ETest {

	private static Integer idVeiculo;
	private static Integer nroFogoPneu;
	
    @Test
    @Order(4)
    void deveCadastrarPneu() {
        PneuDto dto = new PneuDto();
        dto.setMarca("Goodyear");

        ResponseEntity<RespostaDto<PneuDto>> resposta = restTemplate
        		.exchange("/pneu/cadastrar", HttpMethod.POST, new HttpEntity<>(dto), new ParameterizedTypeReference<RespostaDto<PneuDto>>() {});

        assertThat(resposta.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    @Order(5)
    void deveVincularPneuAoVeiculo() {
    	
    	ResponseEntity<RespostaDto<VeiculoDto>> respVeiculo = restTemplate
        		.exchange("/veiculo", HttpMethod.GET, null, new ParameterizedTypeReference<RespostaDto<VeiculoDto>>() {});
    	idVeiculo = respVeiculo.getBody().getRegistros().get(0).getId();

    	ResponseEntity<RespostaDto<PneuDto>> respPneu = restTemplate
        		.exchange("/pneu", HttpMethod.GET, null, new ParameterizedTypeReference<RespostaDto<PneuDto>>() {});
    	nroFogoPneu = respPneu.getBody().getRegistros().get(0).getNumeroFogo();
    	
    	VincularPneuVeiculoDto dto = new VincularPneuVeiculoDto();
        dto.setIdVeiculo(idVeiculo);
        dto.setNroFogoPneu(nroFogoPneu);
        dto.setPosicaoPneu(9);
        dto.setPressaoAtual(BigDecimal.valueOf(32));

        ResponseEntity<RespostaDto<VeiculoDto>> resposta = restTemplate
        		.exchange("/pneu/vincular", HttpMethod.PUT, new HttpEntity<>(dto), new ParameterizedTypeReference<RespostaDto<VeiculoDto>>() {});

        assertThat(resposta.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    @Order(6)
    void deveDesvincularPneu() {

    	assertThat(idVeiculo).isNotNull();
    	assertThat(nroFogoPneu).isNotNull();
    	
        DesvincularPneuVeiculoDto dto = new DesvincularPneuVeiculoDto();
        dto.setIdVeiculo(idVeiculo);
        dto.setNroFogoPneu(nroFogoPneu);

        ResponseEntity<RespostaDto<PneuDto>> resposta = restTemplate
        		.exchange("/pneu/desvincular", HttpMethod.PUT, new HttpEntity<>(dto), new ParameterizedTypeReference<RespostaDto<PneuDto>>() {});

        assertThat(resposta.getStatusCode().is2xxSuccessful()).isTrue();
    }
}

