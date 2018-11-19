package br.com.adaca.mapper;

import br.com.adaca.dto.LabirintoDTO;
import br.com.adaca.model.Labirinto;
import br.com.adaca.util.BaseMapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LabirintoMapper extends BaseMapper<Labirinto, LabirintoDTO> {

}
