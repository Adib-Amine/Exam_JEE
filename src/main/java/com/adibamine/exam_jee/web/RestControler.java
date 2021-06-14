package com.adibamine.exam_jee.web;

import com.adibamine.exam_jee.model.Patient;
import com.adibamine.exam_jee.repository.ConsultationRepository;
import com.adibamine.exam_jee.repository.MedecinRepository;
import com.adibamine.exam_jee.repository.PatientRepository;
import com.adibamine.exam_jee.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin("*")
@RestController
public class RestControler{
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private RendezVousRepository repository;


    @GetMapping(path = "/patient/{id}")
    @Transactional
    public Patient patient(@PathVariable(name = "id") Long id) throws Exception {
        Patient patient = patientRepository.findById(id).get();
        return patient;

    }
    @GetMapping(path = "/patient/all")
    @Transactional
    public List<Patient> patients() throws Exception {
        return patientRepository.findAll();

    }
}
