package com.adibamine.exam_jee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "PATIENTS")
@Data
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "Nom",length = 25)
    private  String nom;
    @Column(name = "Email",length = 100)
    private String email;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="DateNaiss")
    private Date dateNaissance;
    @OneToMany(mappedBy = "patient")
    private Set<RendezVous> rendezVousSet;

}
