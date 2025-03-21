package com.example.intia_assurance_test.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    @ManyToOne
    @JoinColumn(name = "succursale_id")
    private Succursale succursale;
}
