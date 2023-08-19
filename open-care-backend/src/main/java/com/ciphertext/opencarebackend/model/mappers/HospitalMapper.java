package com.ciphertext.opencarebackend.model.mappers;

import com.ciphertext.opencarebackend.model.dto.HospitalDTO;
import com.ciphertext.opencarebackend.model.entity.Hospital;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HospitalMapper {
    HospitalMapper INSTANCE = Mappers.getMapper( HospitalMapper.class );

    HospitalDTO hospitalToHospitalDTO(Hospital Hospital);

    Hospital hospitalDTOToHospital(HospitalDTO HospitalDTO);
}
