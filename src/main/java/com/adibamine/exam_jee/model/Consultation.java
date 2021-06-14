package com.adibamine.exam_jee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "CONSULTATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "Description",length = 200)
    private String description;
    @Column(name = "Traitement",length = 100)
    private String traitement;
    @Column(name = "Type",length = 20)
    private String type;
    @OneToOne
    private RendezVous rendezVous;
}
