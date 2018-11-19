package br.com.adaca.mapper;

import br.com.adaca.dto.MedicamentoDTO;
import br.com.adaca.model.Medicamento;
import br.com.adaca.util.BaseMapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicamentoMapper extends BaseMapper<Medicamento, MedicamentoDTO> {
    
}
