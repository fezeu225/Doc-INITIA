package com.example.intia_assurance_test.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @Temporal(TemporalType.DATE)
    private Date dateSouscription;

    @Temporal(TemporalType.DATE)
    private Date dateExpiration;

    private double montant;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
