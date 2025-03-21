package com.example.intia_assurance_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nom;
    private String email;
    private String password;

    @Column(name = "role")
    @ElementCollection
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @CollectionTable(name = "role")
    private Collection<String> role;

    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "succursale_id")
    private Succursale succursale;
}
