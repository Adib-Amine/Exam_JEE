package com.adibamine.exam_jee;

import com.adibamine.exam_jee.model.Medecin;
import com.adibamine.exam_jee.model.Patient;
import com.adibamine.exam_jee.repository.MedecinRepository;
import com.adibamine.exam_jee.repository.PatientRepository;
import com.adibamine.exam_jee.sid.InitService;
import com.adibamine.exam_jee.sid.InitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ExamJeeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ExamJeeApplication.class, args);
    }
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private InitService initService;

    @Override
    public void run(String... args) throws Exception {
        initService.initPatient();
        initService.initMedecin();
        initService.initRendezVous();
        initService.initConsultation();

//        patientRepository.save(new Patient(null,"Amine","amine@gmail.com",new Date(),null));
//        patientRepository.save(new Patient(null,"Yasssin","yassine@gmail.com",new Date(),null));
//        patientRepository.save(new Patient(null,"Wissal","wissal@gmail.com",new Date(),null));
//        medecinRepository.save(new Medecin(null,"ADIB","Cardiologie",null));
//        medecinRepository.save(new Medecin(null,"SAAD","chirurgie",null));

        System.out.println("****************************************************************");
        patientRepository.findAll().forEach(patient -> {
            System.out.println(patient.getEmail());
        });
    }
}
