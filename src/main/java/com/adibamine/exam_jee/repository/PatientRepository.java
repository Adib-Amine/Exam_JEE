package com.adibamine.exam_jee.repository;

import com.adibamine.exam_jee.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    public List<Patient> findByNomContains(String name);
    public List<Patient> findByEmailContains(String email);
    public Page<Patient> findByNomContains(String name, Pageable pageable);



}
