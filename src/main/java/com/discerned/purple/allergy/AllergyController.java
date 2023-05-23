package com.discerned.purple.allergy;

import com.discerned.purple.patient.Patient;
import com.discerned.purple.patient.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/allergies")
public class AllergyController {
    private final AllergyService allergyService;
    private final PatientService patientService;

    public AllergyController(
            AllergyService allergyService,
            PatientService patientService
    ) {
        this.allergyService = allergyService;
        this.patientService = patientService;
    }

    @GetMapping("/{patientId}/allergy")
    public Set<Allergy> getAllergies(
            @PathVariable("patientId") UUID patientId
    ) {
        Patient patient = patientService.findPatientById(patientId);
        return patient.getAllergies();
    }

    @PostMapping("/{patientId}/allergy/save")
    public Allergy saveAllergy(
            @PathVariable("patientId") UUID patientId,
            @RequestBody Allergy allergy
    ) {
        allergy.setPatient(patientId);
        return allergyService.saveAllergy(allergy);
    }

    @PutMapping("/{patientId}/allergy/{allergyId}/update")
    public ResponseEntity<?> updateAllergy(
            @PathVariable("patientId") UUID patientId,
            @PathVariable("allergyId") Long allergyId,
            @RequestBody Allergy allergyData
    ) {
        var allergy = allergyService.findById(allergyId);
        allergy.setName(allergyData.getName());
        allergy.setDiagnosedDate(allergyData.getDiagnosedDate());
        allergy.setPatient(patientId);
        return ResponseEntity.ok(allergyService.saveAllergy(allergy));
    }

    @DeleteMapping("/{patientId}/{allergyId}/delete")
    public ResponseEntity<?> deleteAllergy(
            @PathVariable("patientId") UUID patientId,
            @PathVariable("allergyId") Long allergyId
    ) {
        var allergy = allergyService.findById(allergyId);
        allergyService.deleteAllergy(allergyId);
        return ResponseEntity.ok(allergy.getName() + " has been deleted");
    }
}
