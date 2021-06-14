package com.adibamine.exam_jee.sid;

import com.adibamine.exam_jee.model.Consultation;
import com.adibamine.exam_jee.model.Medecin;
import com.adibamine.exam_jee.model.Patient;
import com.adibamine.exam_jee.model.RendezVous;
import com.adibamine.exam_jee.repository.ConsultationRepository;
import com.adibamine.exam_jee.repository.MedecinRepository;
import com.adibamine.exam_jee.repository.PatientRepository;
import com.adibamine.exam_jee.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Stream;

@Service
@Transactional
public class InitServiceImpl implements InitService{
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private RendezVousRepository rendezVousRepository;


    @Override
    public void initPatient() {
        Stream.of("AMINE","YASSINE","OTHMAN","MEHDI").forEach(namePatient->{
            Patient patient = new Patient();
            patient.setNom(namePatient);
            patient.setEmail(namePatient+"@gmail.com");
            patient.setRendezVousSet(null);
            patientRepository.save(patient);
        });
    }

    @Override
    public void initMedecin() {
        Stream.of("ADIB","SAAD","MOHAMMED").forEach(nameMEDECIN->{
            Medecin medecin = new Medecin();
            medecin.setNom(nameMEDECIN);
            medecin.setSpecialite("Cardiologie");
            medecin.setRendezVousSet(null);
            medecinRepository.save(medecin);
        });
    }
    @Override
    public void initRendezVous() {
        for (long i = 0L; i < 3L; i++) {
            RendezVous rendezVous = new RendezVous();
            rendezVous.setPatient(patientRepository.findById(1L).get());
            rendezVous.setMedecin(medecinRepository.findById(1L).get());
            rendezVous.setConsultation(null);
            rendezVous.setAnnulee(false);
            rendezVousRepository.save(rendezVous);
        }
    }
    @Override
    public void initConsultation() {
        for (long i = 0L; i < 3L; i++) {
            Consultation consultation = new Consultation();
            consultation.setDescription("test Consultation Description");
            consultation.setTraitement("Test Traitement");
            consultation.setType("presentiel");
            RendezVous rendezVous = rendezVousRepository.findById(1L).get();
            consultation.setRendezVous(rendezVous);
            rendezVous.setConsultation(consultation);
            rendezVousRepository.save(rendezVous);
            consultationRepository.save(consultation);
        }
    }


}
