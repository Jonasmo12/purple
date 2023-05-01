package com.discerned.purple.allergy;

import com.discerned.purple.patient.Patient;
import com.discerned.purple.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
public class AllergyController {
    @Autowired
    private final AllergyService allergyService;
    @Autowired
    private final PatientRepository patientRepository;

    public AllergyController(
            AllergyService allergyService,
            PatientRepository patientRepository
    ) {
        this.allergyService = allergyService;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patient/{patientId}/allergy")
    public Set<Allergy> getAllergies(
            @PathVariable("patientId") Long patientId
    ) {
        Patient patient = patientRepository.findById(patientId).get();
        return patient.getAllergies();
    }

    @PostMapping("/patient/{patientId}/allergy/save")
    public Allergy saveAllergy(
            @PathVariable("patientId") Long patientId,
            @RequestBody Allergy allergy
    ) {
        allergy.setPatient(patientId);
        allergyService.saveAllergy(allergy);
        return allergy;
    }
}
