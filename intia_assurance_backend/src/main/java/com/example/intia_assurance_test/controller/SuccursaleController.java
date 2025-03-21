package com.example.intia_assurance_test.controller;

import com.example.intia_assurance_test.model.Succursale;
import com.example.intia_assurance_test.service.SuccursaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/succursales")
public class SuccursaleController {
    private final SuccursaleService succursaleService;

    public SuccursaleController(SuccursaleService succursaleService) {
        this.succursaleService = succursaleService;
    }

    @GetMapping
    public List<Succursale> getAllSuccursales() {
        return succursaleService.getAllSuccursales();
    }

    @PostMapping
    public Succursale addSuccursale(@RequestBody Succursale succursale) {
        return succursaleService.addSuccursale(succursale);
    }

    @PutMapping("/{id}")
    public Succursale updateSuccursale(@PathVariable Long id, @RequestBody Succursale succursale) {
        return succursaleService.updateSuccursale(id, succursale);
    }

    @DeleteMapping("/{id}")
    public void deleteAssurance(@PathVariable Long id) {
        succursaleService.deleteSuccursale(id);
    }
}
