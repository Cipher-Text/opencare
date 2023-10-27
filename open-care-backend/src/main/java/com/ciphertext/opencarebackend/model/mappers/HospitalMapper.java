package com.ciphertext.opencarebackend.model.mappers;

import com.ciphertext.opencarebackend.model.dto.HospitalDTO;
import com.ciphertext.opencarebackend.model.dto.HospitalDTO;
import com.ciphertext.opencarebackend.model.entity.Hospital;
import com.ciphertext.opencarebackend.model.entity.Hospital;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HospitalMapper extends GenericMapper<HospitalDTO, Hospital> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Hospital partialUpdate(HospitalDTO doctorDTO, @MappingTarget Hospital doctor);
}
