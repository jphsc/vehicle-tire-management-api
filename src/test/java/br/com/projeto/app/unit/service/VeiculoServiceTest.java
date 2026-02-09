package br.com.projeto.app.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.projeto.app.dto.RespostaDto;
import br.com.projeto.app.dto.VeiculoDto;
import br.com.projeto.app.mapper.VeiculoMapper;
import br.com.projeto.app.model.Veiculo;
import br.com.projeto.app.repository.VeiculoRepository;
import br.com.projeto.app.service.VeiculoService;
import br.com.projeto.app.util.Constantes;

@ExtendWith(MockitoExtension.class)
public class VeiculoServiceTest {

    @Mock private VeiculoRepository repository;
    @Mock private VeiculoMapper vMapper;

    @InjectMocks
    private VeiculoService service;

    @Test
    void deveRetornarListaDeVeiculos() {

        when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(new Veiculo())));
        when(vMapper.toDtoSemPneus(any())).thenReturn(new VeiculoDto());

        var resposta = service.getVeiculos(0);

        assertThat(resposta.getQuantidade()).isEqualTo(1);
        verify(repository).findAll(any(Pageable.class));
    }

    @Test
    void deveInformarQuandoNaoExistirVeiculos() {

    	when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of()));
    	
    	RespostaDto<VeiculoDto> resposta = service.getVeiculos(1);
    	
    	assertThat(resposta.getQuantidade()).isEqualTo(0);
    	assertThat(resposta.getMensagem()).isEqualTo(Constantes.MSG_REGISTROS_NAO_ENCONTRADOS);
    }

    @Test
    void deveBuscarVeiculoPorId() {

        Veiculo v = new Veiculo();
        v.setId(1);

        when(repository.findVeiculoByIdWithPneus(1)).thenReturn(Optional.of(v));
        when(vMapper.toDtoComPneus(any())).thenReturn(new VeiculoDto());

        RespostaDto<VeiculoDto> resposta = service.getVeiculoById(1);

        assertThat(resposta.getQuantidade()).isEqualTo(1);
    }

}
