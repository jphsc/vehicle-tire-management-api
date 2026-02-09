package br.com.projeto.app.mapper;

import org.mapstruct.Mapper;

import br.com.projeto.app.dto.PneuDto;
import br.com.projeto.app.model.Pneu;

@Mapper(componentModel = "spring")
public interface PneuMapper {
	
    PneuDto toDto(final Pneu entity);
}
