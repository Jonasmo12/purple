package com.discerned.purple.medication;

import com.discerned.purple.patient.Patient;
import com.discerned.purple.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
public class MedicationController {
    @Autowired
    private final MedicationService medicationService;
    @Autowired
    private final PatientRepository patientRepository;

    public MedicationController(
            MedicationService medicationService,
            PatientRepository patientRepository
    ) {
        this.medicationService = medicationService;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patient/{patientId}/medication/")
    public Set<Medication> getMedications(
            @PathVariable("patientId") Long patientId
    ) {
        Patient patient = patientRepository.findById(patientId).get();
        return patient.getMedications();
    }

    @PostMapping("/patient/{patientId}/medication/save")
    public Medication saveMedication(
            @PathVariable("patientId") Long patientId,
            @RequestBody Medication medication
    ) {
        medication.setPatient(patientId);
        medicationService.saveMedication(medication);
        return medication;
    }
}
