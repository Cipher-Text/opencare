package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Sadman
 */
@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    @Query(value = "SELECT h from Hospital h " +
            "WHERE (:name is null or lower(h.name) like %:name%) AND (:bnName is null or h.bnName like %:bnName%)  " +
            "AND (:numberOfBed is null or h.numberOfBed = :numberOfBed) AND (:districtId is null or h.district.id = :districtId) " +
            "ORDER BY h.id")
    Page<Hospital> getFilteredHospitals(String name, String bnName, Integer numberOfBed, Integer districtId, Pageable pageable);
}
