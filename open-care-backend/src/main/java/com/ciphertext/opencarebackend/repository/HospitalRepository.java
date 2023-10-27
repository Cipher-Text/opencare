package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Sadman
 */
@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer>, JpaSpecificationExecutor<Hospital> {

}
