package com.ciphertext.medicalinformationbackend.controller.api;

import com.ciphertext.medicalinformationbackend.exception.RecordNotFoundException;
import com.ciphertext.medicalinformationbackend.iservice.InstitutionService;
import com.ciphertext.medicalinformationbackend.iservice.SpecialityService;
import com.ciphertext.medicalinformationbackend.model.Speciality;
import com.ciphertext.medicalinformationbackend.model.Institution;
import com.ciphertext.medicalinformationbackend.model.Speciality;
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
public class SpecialityApiController {
    @Autowired
    SpecialityService service;

    @GetMapping("/specialities")
    public List<Speciality> getAllSpecialities(Model model) {
        return service.getAllSpecialities();
    }

    @GetMapping("/specialities/{id}")
    public ResponseEntity<Speciality> getSpecialityById(@PathVariable(value = "id") int specialityId)
            throws RecordNotFoundException {
        Speciality speciality = service.getSpecialityById(specialityId);
        return ResponseEntity.ok().body(speciality);
    }

    @PostMapping("/specialities")
    public Speciality createSpeciality(@Valid @RequestBody Speciality hospital) {
        return service.createSpeciality(hospital);
    }

    @PutMapping("/specialities/edit/{id}")
    public Speciality editSpecialityById(@RequestBody Speciality newSpeciality, @PathVariable(value = "id") int hospitalId) {
        return service.updateSpeciality(newSpeciality, hospitalId);
    }

    @DeleteMapping("/specialities/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteSpecialitiesById(@PathVariable(value = "id") int hospitalId){
        return service.deleteSpecialityById(hospitalId);
    }
}
