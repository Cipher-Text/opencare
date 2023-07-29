package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.dto.out.HospitalDTO;
import com.ciphertext.opencarebackend.dto.out.HospitalDTO;
import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.iservice.HospitalService;
import com.ciphertext.opencarebackend.mappers.HospitalMapper;
import com.ciphertext.opencarebackend.model.Hospital;
import com.ciphertext.opencarebackend.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        List<HospitalDTO> hospitalDTOS= new ArrayList<>();
        for(Hospital hospital: hospitals){
            HospitalDTO hospitalDTO = new HospitalDTO();
            hospitalDTO.setId(hospital.getId());
            hospitalDTO.setName(hospital.getName());
            hospitalDTO.setDistrict(hospital.getDistrict());
            hospitalDTO.setNumberOfBed(hospital.getNumberOfBed());
            hospitalDTOS.add(hospitalDTO);
        }
        return hospitalDTOS;
    }

    @Override
    public Page<HospitalDTO> getPaginatedDataWithFilters(String name, String bnName, Integer numberOfBed,
                                                         Integer districtId, Pageable pagingSort) {
        return hospitalRepository.getFilteredHospitals(name, bnName, numberOfBed, districtId, pagingSort)
                .map(hospitalMapper::hospitalToHospitalDTO);
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


}
