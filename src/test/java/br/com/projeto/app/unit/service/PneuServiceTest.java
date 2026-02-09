package br.com.projeto.app.unit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.projeto.app.domain.exception.GlobalException;
import br.com.projeto.app.dto.PneuDto;
import br.com.projeto.app.dto.RespostaDto;
import br.com.projeto.app.dto.VincularPneuVeiculoDto;
import br.com.projeto.app.mapper.PneuMapper;
import br.com.projeto.app.mapper.VeiculoMapper;
import br.com.projeto.app.model.Pneu;
import br.com.projeto.app.repository.PneuRepository;
import br.com.projeto.app.repository.VeiculoRepository;
import br.com.projeto.app.service.PneuService;

@ExtendWith(MockitoExtension.class)
public class PneuServiceTest {

    @Mock private PneuRepository pneuRepository;
    @Mock private VeiculoRepository veiculoRepository;
    @Mock private PneuMapper pMapper;
    @Mock private VeiculoMapper vMapper;
    
    @InjectMocks 
    private PneuService service;

    @Test
    void deveCadastrarPneuComSucesso() {

        PneuDto dto = new PneuDto();
        dto.setMarca("Goodyear");

        Pneu pneuSalvo = new Pneu();
        pneuSalvo.setNumeroFogo(1);
        pneuSalvo.setMarca("Goodyear");
        pneuSalvo.setPressaoAtual(BigDecimal.ZERO);

        when(pneuRepository.save(any())).thenReturn(pneuSalvo);
        when(pMapper.toDto(any())).thenReturn(new PneuDto());

        RespostaDto<PneuDto> resposta = service.cadastrarPneu(dto);

        assertThat(resposta.getQuantidade()).isEqualTo(1);
    }

    @Test
    void deveLancarErroQuandoVeiculoNaoExiste() {

        VincularPneuVeiculoDto filtro = new VincularPneuVeiculoDto();
        filtro.setIdVeiculo(99);
        filtro.setNroFogoPneu(1);
        filtro.setPosicaoPneu(9);
        filtro.setPressaoAtual(BigDecimal.TEN);

        when(veiculoRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.vincularPneu(filtro)).isInstanceOf(GlobalException.class);
    }
}