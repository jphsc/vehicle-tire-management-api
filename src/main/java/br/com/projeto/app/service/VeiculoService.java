package br.com.projeto.app.service;

import java.util.NoSuchElementException;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.app.domain.enums.Enums;
import br.com.projeto.app.domain.enums.Enums.SituacaoVeiculo;
import br.com.projeto.app.domain.exception.GlobalException;
import br.com.projeto.app.dto.RespostaDto;
import br.com.projeto.app.dto.VeiculoDto;
import br.com.projeto.app.mapper.VeiculoMapper;
import br.com.projeto.app.model.Veiculo;
import br.com.projeto.app.repository.VeiculoRepository;
import br.com.projeto.app.util.Constantes;

@Service
public class VeiculoService {
	
	@Autowired private VeiculoRepository repository;
	@Autowired private VeiculoMapper mapper;

	@Transactional(readOnly = true)
	public RespostaDto<VeiculoDto> getVeiculos(Integer pagina) {
		
		try {
			Integer pagConsulta = (pagina <= 1 || pagina == null) ? 0 : pagina - 1;
			
			Pageable paginavel = PageRequest.of(pagConsulta, Constantes.NRO_MAXIMO_REG_PAGINACAO);
			Page<Veiculo> paginaVeiculos = this.repository.findAll(paginavel);
			Page<VeiculoDto> paginaDto = paginaVeiculos.map(mapper::toDtoSemPneus);
			
			String mensagem = paginaVeiculos.isEmpty() ? Constantes.MSG_REGISTROS_NAO_ENCONTRADOS : Constantes.MSG_REGISTROS_ENCONTRADOS;

			return RespostaDto.of(paginaDto, mensagem);
			
		} catch (NoSuchElementException e) {
			throw new GlobalException(Constantes.COD_ERRO_INEXISTENTE, e.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(Constantes.MSG_ERRO_GERAL);
		}
	}
	
	@Transactional(readOnly = true)
	public RespostaDto<VeiculoDto> getVeiculoById(Integer id) {
		
		try {
			Veiculo veiculo = this.repository.findVeiculoByIdWithPneus(id)
					.orElseThrow(() -> new NoSuchElementException(Constantes.MSG_REGISTROS_NAO_ENCONTRADOS));
			VeiculoDto dto = mapper.toDtoComPneus(veiculo);
			
			return RespostaDto.of(dto, Constantes.MSG_REGISTROS_ENCONTRADOS, null);
			
		} catch (NoSuchElementException e) {
			throw new GlobalException(Constantes.COD_ERRO_INEXISTENTE, e.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(Constantes.MSG_ERRO_GERAL);
		}
	}
	
	public RespostaDto<VeiculoDto> createVeiculo(VeiculoDto filtro) {
		
		try {
			SituacaoVeiculo situacao = Enums.getEnum(SituacaoVeiculo.class, filtro.getStatus(), Constantes.DESC_ENUM_STATUS_VEICULO);
			
			Veiculo v = new Veiculo();
			v.setMarca(filtro.getMarca());
			v.setModelo(filtro.getModelo());
			v.setPlaca(filtro.getPlaca());
			v.setQuilometragem(filtro.getQuilometragem());
			v.setStatus(situacao);
			v = this.repository.save(v);

			VeiculoDto dto = mapper.toDtoSemPneus(v);
			
			return RespostaDto.of(dto, Constantes.MSG_VEICULO_CRIACO_SUCESSO, null);
			
		} catch(DataIntegrityViolationException e) {
			
			if (e.getRootCause() instanceof PropertyValueException) {
				throw new GlobalException(Constantes.COD_ERRO_VALIDACAO, Constantes.MSG_ERRO_CAMPO_OBRIGATORIO_FALTANTE);
			} else {
				throw new GlobalException(Constantes.COD_ERRO_VALIDACAO, Constantes.MSG_ERRO_PLACA_EXISTENTE);
			}
			
		} catch(NoSuchElementException e) {
			throw new GlobalException(Constantes.COD_ERRO_VALIDACAO, e.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException(Constantes.MSG_ERRO_GERAL);
		}
	}
 
}
