package com.ciphertext.opencarebackend.model.mappers;

import com.ciphertext.opencarebackend.model.dto.MedicalSpecialityDTO;
import com.ciphertext.opencarebackend.model.entity.MedicalSpeciality;
import com.ciphertext.opencarebackend.model.mappers.GenericMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicalSpecialityMapper extends GenericMapper<MedicalSpecialityDTO, MedicalSpeciality> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MedicalSpeciality partialUpdate(MedicalSpecialityDTO medicalSpecialityDTO, @MappingTarget MedicalSpeciality medicalSpeciality);
}