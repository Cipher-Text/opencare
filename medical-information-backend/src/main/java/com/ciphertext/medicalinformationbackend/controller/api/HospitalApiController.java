package com.ciphertext.medicalinformationbackend.controller.api;

import com.ciphertext.medicalinformationbackend.dto.out.HospitalDTO;
import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.iservice.HospitalService;
import com.ciphertext.medicalinformationbackend.model.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Sadman
 */
@RestController
@RequestMapping("/api/v1")
public class HospitalApiController {
    @Autowired
    HospitalService service;

    @GetMapping("/hospitals")
    public List<HospitalDTO> getAllHospitals(Model model) {
        return service.getAllHospitals();
    }

    @GetMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable(value = "id") int hospitalId)
            throws RecordNotFoundException {
        Hospital hospital = service.getHospitalById(hospitalId);
        return ResponseEntity.ok().body(hospital);
    }

    @PostMapping("/hospitals")
    public Hospital createHospital(@Valid @RequestBody Hospital hospital) {
        return service.createHospital(hospital);
    }

    @PutMapping("/hospitals/edit/{id}")
    public Hospital editHospitalById(@RequestBody Hospital newHospital, @PathVariable(value = "id") int hospitalId) {
        return service.updateHospital(newHospital, hospitalId);
    }

    @DeleteMapping("/hospitals/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteHospitalsById(@PathVariable(value = "id") int hospitalId){
        return service.deleteHospitalById(hospitalId);
    }
}
