package com.ciphertext.opencarebackend.mappers;

import com.ciphertext.opencarebackend.dto.out.HospitalDTO;
import com.ciphertext.opencarebackend.model.Hospital;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HospitalMapper {
    HospitalMapper INSTANCE = Mappers.getMapper( HospitalMapper.class );

    HospitalDTO hospitalToHospitalDTO(Hospital Hospital);

    Hospital hospitalDTOToHospital(HospitalDTO HospitalDTO);
}
