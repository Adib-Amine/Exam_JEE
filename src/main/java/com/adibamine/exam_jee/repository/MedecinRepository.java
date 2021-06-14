package com.adibamine.exam_jee.repository;

import com.adibamine.exam_jee.model.Medecin;
import com.adibamine.exam_jee.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin,Long> {
    public List<Medecin> findByNomContains(String name);
    public Page<Medecin> findByNomContains(String name, Pageable pageable);

}
