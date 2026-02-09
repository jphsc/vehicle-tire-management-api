package br.com.projeto.app.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.projeto.app.domain.enums.Enums;
import br.com.projeto.app.domain.enums.Enums.PosicoesPneu;
import br.com.projeto.app.domain.enums.Enums.StatusPneu;
import br.com.projeto.app.domain.exception.GlobalException;
import br.com.projeto.app.dto.DesvincularPneuVeiculoDto;
import br.com.projeto.app.dto.PneuDto;
import br.com.projeto.app.dto.RespostaDto;
import br.com.projeto.app.dto.VeiculoDto;
import br.com.projeto.app.dto.VincularPneuVeiculoDto;
import br.com.projeto.app.mapper.PneuMapper;
import br.com.projeto.app.mapper.VeiculoMapper;
import br.com.projeto.app.model.Pneu;
import br.com.projeto.app.model.Veiculo;
import br.com.projeto.app.repository.PneuRepository;
import br.com.projeto.app.repository.VeiculoRepository;
import br.com.projeto.app.util.Constantes;
import jakarta.transaction.Transactional;

@Service
public class PneuService {

	@Autowired private PneuRepository pneuRepository;
	@Autowired private VeiculoRepository veiculoRepository;
	@Autowired private PneuMapper pMapper;
	@Autowired private VeiculoMapper vMapper;

	public RespostaDto<PneuDto> obterPneus(Integer pagina) {

		try {
			Integer pagConsulta = (pagina <= 1 || pagina == null) ? 0 : pagina - 1;
			
			Pageable paginavel = PageRequest.of(pagConsulta, Constantes.NRO_MAXIMO_REG_PAGINACAO, Sort.by("numeroFogo").ascending());
			Page<Pneu> paginaPneus = this.pneuRepository.findAll(paginavel);
			Page<PneuDto> paginaDto = paginaPneus.map(pMapper::toDto);
			
			String mensagem = paginaPneus.isEmpty() ? Constantes.MSG_REGISTROS_NAO_ENCONTRADOS : Constantes.MSG_REGISTROS_ENCONTRADOS;

			return RespostaDto.of(paginaDto, mensagem);
			
		} catch (NoSuchElementException e) {
			throw new GlobalException(Constantes.COD_ERRO_INEXISTENTE, e.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(Constantes.MSG_ERRO_GERAL);
		}
	}
	
	public RespostaDto<PneuDto> cadastrarPneu(PneuDto filtro) {

		try {
			Pneu p = new Pneu();
			p.setMarca(filtro.getMarca());
			p.setPressaoAtual(BigDecimal.ZERO);
			p.setStatus(StatusPneu.DISPONIVEL);
			p = this.pneuRepository.save(p);

			PneuDto dto = pMapper.toDto(p);
			
			return RespostaDto.of(dto, Constantes.MSG_SUCESSO_PNEU_CADASTRADO, null);
				
		} catch(DataIntegrityViolationException e) {
			throw new GlobalException(Constantes.COD_ERRO_VALIDACAO, Constantes.MSG_ERRO_CAMPO_OBRIGATORIO_FALTANTE);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(Constantes.COD_ERRO_INTERNO, Constantes.MSG_ERRO_GERAL);
		}
	}
	
	@Transactional
	public RespostaDto<VeiculoDto> vincularPneu(VincularPneuVeiculoDto filtro) {
		
		try {
			PosicoesPneu posicaoPneu = Enums.getEnum(PosicoesPneu.class, filtro.getPosicaoPneu(), Constantes.DESC_ENUM_POSICAO_PNEU);
			
			Veiculo vPersistente = this.veiculoRepository.findById(filtro.getIdVeiculo())
					.orElseThrow(() -> new NoSuchElementException(Constantes.MSG_ERRO_VEICULO_NAO_ENCONTRADO));
			Pneu pPersistente = this.pneuRepository.findById(filtro.getNroFogoPneu())
					.orElseThrow(() -> new NoSuchElementException(Constantes.MSG_ERRO_PNEU_NAO_ENCONTRADO));

			Boolean posPneuVeiculoOcup = vPersistente.getPneus().stream().anyMatch(p -> p.getPosicaoPneu().equals(filtro.getPosicaoPneu()));
			
			if(Objects.nonNull(pPersistente.getVeiculo()) && pPersistente.getVeiculo().getId().equals(vPersistente.getId())) {
				throw new GlobalException(Constantes.COD_ERRO_VALIDACAO, Constantes.MSG_ERRO_PNEU_VINCULADO_ESSE_VEICULO);
				
			} else if(Objects.nonNull(pPersistente.getVeiculo()) && !pPersistente.getVeiculo().getId().equals(vPersistente.getId())) {
				throw new GlobalException(Constantes.COD_ERRO_VALIDACAO, Constantes.MSG_ERRO_PNEU_VINCULADO_OUTRO_VEICULO);
				
			} else if (posPneuVeiculoOcup) {
				throw new GlobalException(Constantes.COD_ERRO_VALIDACAO, Constantes.MSG_ERRO_POSICAO_PNEU_VINCULADO_TEM_OUTRO_PNEU);
			}
			
			pPersistente.setPosicaoPneu(posicaoPneu);
			pPersistente.setPressaoAtual(filtro.getPressaoAtual());
			pPersistente.setStatus(StatusPneu.EM_USO);
			pPersistente.setVeiculo(vPersistente);
			
			vPersistente.getPneus().add(pPersistente);
			vPersistente = this.veiculoRepository.save(vPersistente);

			VeiculoDto dto = vMapper.toDtoComPneus(vPersistente);
			
			return RespostaDto.of(dto, Constantes.MSG_SUCESSO_PNEU_VINCULADO, null);
			
		} catch (NoSuchElementException e) {
			throw new GlobalException(Constantes.COD_ERRO_VALIDACAO, e.getMessage());
			
		} catch(InvalidDataAccessApiUsageException | DataIntegrityViolationException | NullPointerException e) {
			throw new GlobalException(Constantes.COD_ERRO_VALIDACAO, Constantes.MSG_ERRO_CAMPO_OBRIGATORIO_FALTANTE);
			
		} catch (GlobalException e) {
			throw new GlobalException(Constantes.COD_ERRO_VALIDACAO, e.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(Constantes.MSG_ERRO_GERAL);
		}
	}
	
	@Transactional
	public RespostaDto<PneuDto> desvincularPneu(DesvincularPneuVeiculoDto filtro) {
		
		try {
			Veiculo v = this.veiculoRepository.findById(filtro.getIdVeiculo())
					.orElseThrow(() -> new NoSuchElementException(Constantes.MSG_ERRO_VEICULO_NAO_ENCONTRADO));
			Pneu p = this.pneuRepository.findById(filtro.getNroFogoPneu())
					.orElseThrow(() -> new NoSuchElementException(Constantes.MSG_ERRO_PNEU_NAO_ENCONTRADO));
			
			Boolean pneuVinculadoVeiculo = v.getPneus().stream().anyMatch(vp -> vp.getNumeroFogo().equals(filtro.getNroFogoPneu()));
			
			if(!pneuVinculadoVeiculo) {
				throw new GlobalException(Constantes.COD_ERRO_VALIDACAO, Constantes.MSG_ERRO_PNEU_VINCULADO_OUTRO_VEICULO);
			}
			
			v.getPneus().remove(p);
			p.setVeiculo(null);
			p.setPosicaoPneu(null);
			p.setPressaoAtual(BigDecimal.ZERO);
			p.setStatus(StatusPneu.DISPONIVEL);
			
			this.pneuRepository.save(p);

			PneuDto dto = pMapper.toDto(p);
			
			return RespostaDto.of(dto, Constantes.MSG_SUCESSO_PNEU_DESVINCULADO, null);
			
		} catch (NullPointerException | InvalidDataAccessApiUsageException e) {
			throw new GlobalException(Constantes.COD_ERRO_VALIDACAO, Constantes.MSG_ERRO_CAMPO_OBRIGATORIO_FALTANTE);
			
		} catch (NoSuchElementException e) {
			throw new GlobalException(Constantes.COD_ERRO_INEXISTENTE, e.getMessage());
			
		} catch (GlobalException e) {
			throw new GlobalException(e.getCodigoErro(), e.getDescricaoErro());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(Constantes.MSG_ERRO_GERAL);
		}
	}
	
}
