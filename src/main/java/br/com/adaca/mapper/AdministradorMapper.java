package br.com.adaca.mapper;

import br.com.adaca.dto.AdministradorDTO;
import br.com.adaca.model.Administrador;
import br.com.adaca.util.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdministradorMapper extends BaseMapper<Administrador, AdministradorDTO> {

}
