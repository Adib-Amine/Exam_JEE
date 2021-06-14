package com.adibamine.exam_jee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "MEDECIN")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "Nom",length = 25)
    private  String nom;
    @Column(name = "Specialite",length = 100)
    private String specialite;
    @OneToMany(mappedBy = "medecin")
    private Set<RendezVous> rendezVousSet;

}
