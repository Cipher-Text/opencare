package com.ciphertext.opencarebackend.model.mappers;

import com.ciphertext.opencarebackend.model.dto.DoctorDTO;
import com.ciphertext.opencarebackend.model.entity.Doctor;
import com.ciphertext.opencarebackend.model.enums.Gender;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DoctorMapper extends GenericMapper<DoctorDTO, Doctor> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Doctor partialUpdate(DoctorDTO doctorDTO, @MappingTarget Doctor doctor);

    @Override
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "fromEnumToString")
    DoctorDTO entityToDto(Doctor entity);

    @Override
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "fromStringToEnum")
    Doctor dtoToEntity(DoctorDTO dto);

    @Named("fromEnumToString")
    default String fromEnumToString(Gender gender) {
        return gender.name();
    }

    @Named("fromStringToEnum")
    default Gender fromStringToEnum(String gender) {
        return Gender.valueOf(gender.toUpperCase());
    }
}