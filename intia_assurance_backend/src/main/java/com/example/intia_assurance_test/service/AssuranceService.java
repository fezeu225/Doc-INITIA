package com.example.intia_assurance_test.service;

import com.example.intia_assurance_test.model.Assurance;
import com.example.intia_assurance_test.repository.AssuranceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssuranceService {
    private final AssuranceRepository assuranceRepository;

    public AssuranceService(AssuranceRepository assuranceRepository) {
        this.assuranceRepository = assuranceRepository;
    }

    public List<Assurance> getAllAssurances() {
        return assuranceRepository.findAll();
    }

    public Assurance addAssurance(Assurance assurance) {
        return assuranceRepository.save(assurance);
    }

    public Assurance updateAssurance(Long id, Assurance assurance) {
        assurance.setId(id);
        return assuranceRepository.save(assurance);
    }

    public void deleteAssurance(Long id) {
        assuranceRepository.deleteById(id);
    }

}