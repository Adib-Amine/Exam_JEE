package com.adibamine.exam_jee.repository;

import com.adibamine.exam_jee.model.Patient;
import com.adibamine.exam_jee.model.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    public Page<RendezVous> findAll( Pageable pageable);

}
