package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.model.dto.HospitalDTO;
import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.enums.HospitalType;
import com.ciphertext.opencarebackend.model.enums.OrganizationType;
import com.ciphertext.opencarebackend.model.filter.HospitalFilter;
import com.ciphertext.opencarebackend.repository.specification.Filter;
import com.ciphertext.opencarebackend.service.HospitalService;
import com.ciphertext.opencarebackend.model.mappers.HospitalMapper;
import com.ciphertext.opencarebackend.model.entity.Hospital;
import com.ciphertext.opencarebackend.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ciphertext.opencarebackend.repository.specification.QueryFilterUtils.generateIndividualFilter;
import static com.ciphertext.opencarebackend.repository.specification.QueryFilterUtils.generateJoinTableFilter;
import static com.ciphertext.opencarebackend.repository.specification.QueryOperator.*;
import static com.ciphertext.opencarebackend.repository.specification.SpecificationBuilder.createSpecification;
import static org.springframework.data.jpa.domain.Specification.where;

/**
 * @author Sadman
 */
@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalMapper hospitalMapper = Mappers.getMapper(HospitalMapper.class);

    private final HospitalRepository hospitalRepository;

    @Override
    public List<HospitalDTO> getAllHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        return hospitalMapper.entityToDto(hospitals);
    }

    @Override
    public Page<HospitalDTO> getPaginatedDataWithFilters(HospitalFilter hospitalFilter, Pageable pagingSort) {

        List<Filter> filterList = generateQueryFilters(hospitalFilter);

        Specification<Hospital> specification = where(null);
        if(!filterList.isEmpty()) {
            specification = where(createSpecification(filterList.remove(0)));
            for (Filter input : filterList) {
                specification = specification.and(createSpecification(input));
            }
        }

        Page<Hospital> hospitalPage = hospitalRepository.findAll(specification, pagingSort);

        return hospitalPage.map(hospitalMapper::entityToDto);
    }

    @Override
    public Hospital getHospitalById(int id) throws ResourceNotFoundException {
        return hospitalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Hospital with id = " + id));
    }

    @Override
    public Hospital createHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @Override
    public Hospital updateHospital(Hospital newHospital, int hospitalId) {
        return hospitalRepository.findById(hospitalId)
                .map(hospital -> {
                    hospital.setName(newHospital.getName());
                    hospital.setNumberOfBed(newHospital.getNumberOfBed());
                    hospital.setDistrict(newHospital.getDistrict());
                    return hospitalRepository.save(hospital);
                })
                .orElseGet(() -> {
                    newHospital.setId(hospitalId);
                    return hospitalRepository.save(newHospital);
                });
    }

    @Override
    public ResponseEntity<Object> deleteHospitalById(int hospitalId) {
        hospitalRepository.deleteById(hospitalId);
        if (hospitalRepository.findById(hospitalId).isPresent()) {
            return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
        } else return ResponseEntity.ok().body("Hospital is Deleted Successfully");
    }

    public List<Filter> generateQueryFilters(HospitalFilter hospitalFilter) {

        List<Filter> filters = new ArrayList<>();

        if (hospitalFilter.getName() != null)
            filters.add(generateIndividualFilter("name", LIKE, hospitalFilter.getName()));

        if (hospitalFilter.getBnName() != null)
            filters.add(generateIndividualFilter("bnName", LIKE, hospitalFilter.getBnName()));

        if (hospitalFilter.getBnName() != null)
            filters.add(generateIndividualFilter("numberOfBed", EQUALS, hospitalFilter.getNumberOfBed()));

        if (hospitalFilter.getDistrictId() != null)
            filters.add(generateJoinTableFilter("id", "district", JOIN, hospitalFilter.getDistrictId()));

        if (hospitalFilter.getUpazilaId() != null)
            filters.add(generateJoinTableFilter("id", "upazila", JOIN, hospitalFilter.getUpazilaId()));

        if (hospitalFilter.getUnionId() != null)
            filters.add(generateJoinTableFilter("id", "union", JOIN, hospitalFilter.getUnionId()));

        if (hospitalFilter.getHospitalType() != null)
            filters.add(generateIndividualFilter("hospitalType", EQUALS, HospitalType.valueOf(hospitalFilter.getHospitalType())));

        if (hospitalFilter.getOrganizationType() != null)
            filters.add(generateIndividualFilter("organizationType", EQUALS, OrganizationType.valueOf(hospitalFilter.getOrganizationType())));

        return filters;
    }
}
