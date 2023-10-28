package com.ciphertext.opencarebackend.model.mappers;

import com.ciphertext.opencarebackend.model.dto.InstitutionDTO;
import com.ciphertext.opencarebackend.model.entity.Institution;
import org.mapstruct.*;

/**
 * @author sadman @Date ২৭/৬/২৩
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface InstitutionMapper extends GenericMapper<InstitutionDTO, Institution> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Institution partialUpdate(InstitutionDTO doctorDTO, @MappingTarget Institution doctor);
}
