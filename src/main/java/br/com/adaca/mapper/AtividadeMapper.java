package br.com.adaca.mapper;

import br.com.adaca.dto.AtividadeDTO;
import br.com.adaca.model.Atividade;
import br.com.adaca.util.BaseMapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AtividadeMapper extends BaseMapper<Atividade, AtividadeDTO> {

}
