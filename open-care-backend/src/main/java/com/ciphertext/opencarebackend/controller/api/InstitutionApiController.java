package com.ciphertext.opencarebackend.controller.api;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.iservice.InstitutionService;
import com.ciphertext.opencarebackend.model.Institution;
import com.ciphertext.opencarebackend.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sadman
 */
@RestController
@RequestMapping("/api")
public class InstitutionApiController {
    @Autowired
    InstitutionService service;
    
    @Autowired
    InstitutionRepository institutionRepository;

    @GetMapping("/institutions")
    public ResponseEntity<Map<String, Object>> getAllInstitutionsPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String bnName,
            @RequestParam(required = false) Integer enroll,
            @RequestParam(required = false) Integer districtId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        try {
            Pageable pagingSort = PageRequest.of(page, size);
            Page<Institution> pageInstitutions = institutionRepository.getFilteredInstitutions(name, bnName, enroll, districtId, pagingSort);

            Map<String, Object> response = new HashMap<>();
            response.put("institutions", pageInstitutions.getContent());
            response.put("currentPage", pageInstitutions.getNumber());
            response.put("totalItems", pageInstitutions.getTotalElements());
            response.put("totalPages", pageInstitutions.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/institutions/{id}")
    public ResponseEntity<Institution> getDivisionById(@PathVariable(value = "id") int institutionId)
            throws ResourceNotFoundException {
        Institution institution = service.getInstitutionById(institutionId);
        return ResponseEntity.ok().body(institution);
    }
}
