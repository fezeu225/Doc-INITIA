package com.example.intia_assurance_test.repository;

import com.example.intia_assurance_test.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
