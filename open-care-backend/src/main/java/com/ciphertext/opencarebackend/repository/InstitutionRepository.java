package com.ciphertext.opencarebackend.repository;

import com.ciphertext.opencarebackend.model.entity.Institution;
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
public interface InstitutionRepository extends JpaRepository<Institution, Integer>, JpaSpecificationExecutor<Institution> {

    @Query(value = "SELECT i from Institution i " +
            "WHERE (:name is null or lower(i.name) like %:name%) AND (:bnName is null or i.bnName like %:bnName%)  " +
            "AND (:enroll is null or i.enroll = :enroll) AND (:districtId is null or i.district.id = :districtId) " +
            "ORDER BY i.id")
    Page<Institution> getFilteredInstitutions(String name, String bnName, Integer enroll, Integer districtId, Pageable pageable);
}