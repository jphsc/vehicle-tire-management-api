package br.com.projeto.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.projeto.app.dto.VeiculoDto;
import br.com.projeto.app.model.Veiculo;

@Mapper(componentModel = "spring", uses = PneuMapper.class)
public interface VeiculoMapper {

	@Mapping(target = "pneus", ignore = true)
    VeiculoDto toDtoSemPneus(final Veiculo entity);
	
	VeiculoDto toDtoComPneus(final Veiculo entity);
}
