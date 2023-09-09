package com.ciphertext.opencarebackend.model.mappers;

import com.ciphertext.opencarebackend.model.dto.InstitutionDTO;
import com.ciphertext.opencarebackend.model.entity.Institution;
import org.mapstruct.Mapper;
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
