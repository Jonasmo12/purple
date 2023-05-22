package com.discerned.purple.allergy;

import com.discerned.purple.patient.Patient;
import com.discerned.purple.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/allergies")
public class AllergyController {
    private final AllergyService allergyService;
    private final PatientRepository patientRepository;

    public AllergyController(
            AllergyService allergyService,
            PatientRepository patientRepository
    ) {
        this.allergyService = allergyService;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/{patientId}/allergy")
    public Set<Allergy> getAllergies(
            @PathVariable("patientId") UUID patientId
    ) {
        Patient patient = patientRepository.findById(patientId).get();
        return patient.getAllergies();
    }

    @PostMapping("/{patientId}/allergy/save")
    public Allergy saveAllergy(
            @PathVariable("patientId") UUID patientId,
            @RequestBody Allergy allergy
    ) {
        allergy.setPatient(patientId);
        allergyService.saveAllergy(allergy);
        return allergy;
    }
}
