package com.example.intia_assurance_test.controller;

import com.example.intia_assurance_test.model.Assurance;
import com.example.intia_assurance_test.service.AssuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assurances")
public class AssuranceController {
    private final AssuranceService assuranceService;

    public AssuranceController(AssuranceService assuranceService) {
        this.assuranceService = assuranceService;
    }

    @GetMapping
    public List<Assurance> getAllAssurances() {
        return assuranceService.getAllAssurances();
    }

    @PostMapping
    public Assurance addAssurance(@RequestBody Assurance assurance) {
        return assuranceService.addAssurance(assurance);
    }

    @PutMapping("/{id}")
    public Assurance updateAssurance(@PathVariable Long id, @RequestBody Assurance assurance) {
        return assuranceService.updateAssurance(id, assurance);
    }

    @DeleteMapping("/{id}")
    public void deleteAssurance(@PathVariable Long id) {
        assuranceService.deleteAssurance(id);
    }
}
