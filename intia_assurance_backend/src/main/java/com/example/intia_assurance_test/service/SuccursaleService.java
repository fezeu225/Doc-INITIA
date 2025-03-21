package com.example.intia_assurance_test.service;

import com.example.intia_assurance_test.model.Succursale;
import com.example.intia_assurance_test.repository.SuccursaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuccursaleService {
    private final SuccursaleRepository succursaleRepository;

    public SuccursaleService(SuccursaleRepository succursaleRepository) {
        this.succursaleRepository = succursaleRepository;
    }

    public List<Succursale> getAllSuccursales() {
        return succursaleRepository.findAll();
    }

    public Succursale addSuccursale(Succursale succursale) {
        return succursaleRepository.save(succursale);
    }

    public Succursale updateSuccursale(Long id, Succursale succursale) {
        succursale.setId(id);
        return succursaleRepository.save(succursale);
    }

    public void deleteSuccursale(Long id) {
        succursaleRepository.deleteById(id);
    }
}