package br.com.adaca.mapper;

import br.com.adaca.dto.ResponsavelDTO;
import br.com.adaca.model.Responsavel;
import br.com.adaca.util.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResponsavelMapper extends BaseMapper<Responsavel, ResponsavelDTO> {

}
