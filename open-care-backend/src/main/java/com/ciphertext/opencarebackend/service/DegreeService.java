package com.ciphertext.opencarebackend.service;

import com.ciphertext.opencarebackend.exception.ResourceNotFoundException;
import com.ciphertext.opencarebackend.model.entity.Degree;

import java.util.List;

/**
 * @author Sadman
 */
public interface DegreeService {
    List<Degree> getAllDegrees();
    Degree getDegreeById(int id) throws ResourceNotFoundException;
}
