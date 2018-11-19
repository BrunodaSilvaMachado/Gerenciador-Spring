package br.com.adaca.mapper;

import br.com.adaca.dto.AutistaDTO;
import br.com.adaca.model.Autista;
import br.com.adaca.util.BaseMapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AutistaMapper extends BaseMapper<Autista, AutistaDTO> {

}
