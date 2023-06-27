package com.ciphertext.opencarebackend.controller.api;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.iservice.MedicalSpecialityService;
import com.ciphertext.opencarebackend.model.MedicalSpeciality;
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
@RequestMapping("/api")
public class SpecialityApiController {
    @Autowired
    MedicalSpecialityService service;

    @GetMapping("/specialities")
    public List<MedicalSpeciality> getAllSpecialities(Model model) {
        return service.getAllSpecialities();
    }

    @GetMapping("/specialities/{id}")
    public ResponseEntity<MedicalSpeciality> getSpecialityById(@PathVariable(value = "id") int specialityId)
            throws ResourceNotFoundException {
        MedicalSpeciality medicalSpeciality = service.getSpecialityById(specialityId);
        return ResponseEntity.ok().body(medicalSpeciality);
    }

    @PostMapping("/specialities")
    public MedicalSpeciality createSpeciality(@Valid @RequestBody MedicalSpeciality hospital) {
        return service.createSpeciality(hospital);
    }

    @PutMapping("/specialities/edit/{id}")
    public MedicalSpeciality editSpecialityById(@RequestBody MedicalSpeciality newMedicalSpeciality, @PathVariable(value = "id") int hospitalId) {
        return service.updateSpeciality(newMedicalSpeciality, hospitalId);
    }

    @DeleteMapping("/specialities/delete/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteSpecialitiesById(@PathVariable(value = "id") int hospitalId){
        return service.deleteSpecialityById(hospitalId);
    }
}
