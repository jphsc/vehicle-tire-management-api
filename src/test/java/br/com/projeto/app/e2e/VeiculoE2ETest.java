package br.com.projeto.app.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import br.com.projeto.app.dto.RespostaDto;
import br.com.projeto.app.dto.VeiculoDto;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VeiculoE2ETest extends BaseE2ETest {
	
	private static Integer idVeiculoCriado;
	
	@Test
	@Order(1)
	void deveBuscarListaVeiculos() {
		ResponseEntity<RespostaDto<VeiculoDto>> resposta = restTemplate
				.exchange("/veiculo", HttpMethod.GET, null, new ParameterizedTypeReference<RespostaDto<VeiculoDto>>() {});
		
		assertThat(resposta.getStatusCode().is2xxSuccessful()).isTrue();
		assertThat(resposta.getBody().getQuantidade()).isGreaterThanOrEqualTo(1);
	}
	
	@Test
    @Order(2)
    void deveCriarVeiculoComSucesso() {
        VeiculoDto dto = new VeiculoDto();
        dto.setMarca("Mercedes");
        dto.setModelo("Actros");
        dto.setPlaca("ABC1234");
        dto.setQuilometragem(1000);
        dto.setStatus(2);

        ResponseEntity<RespostaDto<VeiculoDto>> resposta = restTemplate
				.exchange("/veiculo", HttpMethod.POST, new HttpEntity<>(dto), new ParameterizedTypeReference<RespostaDto<VeiculoDto>>() {});

        assertThat(resposta.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(resposta.getBody().getQuantidade()).isEqualTo(1);
        
        idVeiculoCriado = resposta.getBody().getRegistros().get(0).getId();
    }

    @Test
    @Order(3)
    void deveBuscarVeiculoPorId() {
    	
    	assertThat(idVeiculoCriado).isNotNull();
    	
        ResponseEntity<RespostaDto<VeiculoDto>> resposta = restTemplate
        		.exchange(String.format("/veiculo/%d", idVeiculoCriado), 
        				HttpMethod.GET, null, new ParameterizedTypeReference<RespostaDto<VeiculoDto>>() {});

        assertThat(resposta.getStatusCode().is2xxSuccessful()).isTrue();
    }
}
