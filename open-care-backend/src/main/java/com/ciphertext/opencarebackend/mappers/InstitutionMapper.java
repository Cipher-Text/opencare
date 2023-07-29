package com.ciphertext.opencarebackend.mappers;

import com.ciphertext.opencarebackend.dto.out.InstitutionDTO;
import com.ciphertext.opencarebackend.model.Institution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author sadman @Date ২৭/৬/২৩
 */
@Mapper
public interface InstitutionMapper {
    InstitutionMapper INSTANCE = Mappers.getMapper( InstitutionMapper.class );

    InstitutionDTO institutionToInstitutionDTO(Institution institution);

    Institution institutionDTOToInstitution(InstitutionDTO institutionDTO);
}
