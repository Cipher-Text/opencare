package com.ciphertext.opencarebackend.service.impl;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.dto.DoctorDTO;
import com.ciphertext.opencarebackend.model.entity.Doctor;
import com.ciphertext.opencarebackend.model.filter.DoctorFilter;
import com.ciphertext.opencarebackend.model.mappers.DoctorMapper;
import com.ciphertext.opencarebackend.repository.DoctorRepository;
import com.ciphertext.opencarebackend.repository.specification.Filter;
import com.ciphertext.opencarebackend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class DoctorServiceImpl implements DoctorService {
    private final DoctorMapper doctorMapper;
    private final DoctorRepository doctorRepository;

    @Override
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctorMapper.entityToDto(doctors);
    }

    @Override
    public Page<DoctorDTO> getPaginatedDataWithFilters(DoctorFilter doctorFilter, Pageable pagingSort) {


        List<Filter> filterList = generateQueryFilters(doctorFilter);

        Specification<Doctor> specification = where(null);
        if(!filterList.isEmpty()) {
            specification = where(createSpecification(filterList.remove(0)));
            for (Filter input : filterList) {
                specification = specification.and(createSpecification(input));
            }
        }

        Page<Doctor> doctorPage = doctorRepository.findAll(specification, pagingSort);

        return doctorPage.map(doctorMapper::entityToDto);
    }

    @Override
    public Doctor getDoctorById(Long id) throws ResourceNotFoundException {
        return doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Doctor with id = " + id));
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor newDoctor, Long doctorId) {
        return doctorRepository.findById(doctorId)
                .map(doctor -> {
                    doctor.setName(newDoctor.getName());
                    doctor.setBnName(newDoctor.getBnName());

                    return doctorRepository.save(doctor);
                })
                .orElseGet(() -> {
                    newDoctor.setId(doctorId);
                    return doctorRepository.save(newDoctor);
                });
    }

    @Override
    public ResponseEntity<Object> deleteDoctorById(Long doctorId) {
        doctorRepository.deleteById(doctorId);
        if (doctorRepository.findById(doctorId).isPresent()) {
            return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
        } else return ResponseEntity.ok().body("Doctor is Deleted Successfully");
    }

    public List<Filter> generateQueryFilters(DoctorFilter doctorFilter) {

        List<Filter> filters = new ArrayList<>();

        if (doctorFilter.getName() != null)
            filters.add(generateIndividualFilter("name", LIKE, doctorFilter.getName()));

        if (doctorFilter.getBnName() != null)
            filters.add(generateIndividualFilter("bnName", LIKE, doctorFilter.getBnName()));

        if (doctorFilter.getBmdcNo() != null)
            filters.add(generateIndividualFilter("bmdcNo", EQUALS, doctorFilter.getBmdcNo()));

        if (doctorFilter.getDistrictId() != null)
            filters.add(generateJoinTableFilter("id", "district", JOIN, doctorFilter.getDistrictId()));

        if (doctorFilter.getUpazilaId() != null)
            filters.add(generateJoinTableFilter("id", "upazila", JOIN, doctorFilter.getUpazilaId()));

        if (doctorFilter.getUnionId() != null)
            filters.add(generateJoinTableFilter("id", "union", JOIN, doctorFilter.getUnionId()));

        return filters;
    }
}
