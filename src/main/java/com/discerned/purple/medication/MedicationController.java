package com.discerned.purple.medication;

import com.discerned.purple.patient.Patient;
import com.discerned.purple.patient.PatientRepository;
import com.discerned.purple.patient.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/medication")
public class MedicationController {
    private final MedicationService medicationService;
    private final PatientRepository patientRepository;
    private final PatientService patientService;

    public MedicationController(
            MedicationService medicationService,
            PatientRepository patientRepository,
            PatientService patientService) {
        this.medicationService = medicationService;
        this.patientRepository = patientRepository;
        this.patientService = patientService;
    }

    @GetMapping("/patient/{patientId}/medication/")
    public Set<Medication> getMedications(
            @PathVariable("patientId") UUID patientId
    ) {
        Optional<Patient> patient = patientService.findPatientById(patientId);
        return patient.map(Patient::getMedications).orElse(null);
    }

    @PostMapping("/patient/{patientId}/medication/save")
    public Medication saveMedication(
            @PathVariable("patientId") UUID patientId,
            @RequestBody Medication medication
    ) {
        medication.setPatient(patientId);
        medicationService.saveMedication(medication);
        return medication;
    }
}
