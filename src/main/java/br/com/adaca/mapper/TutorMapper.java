package br.com.adaca.mapper;

import br.com.adaca.dto.TutorDTO;
import br.com.adaca.model.Tutor;
import br.com.adaca.util.BaseMapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TutorMapper extends BaseMapper<Tutor, TutorDTO> {

}
