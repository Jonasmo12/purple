package com.discerned.purple.diagnosed;

import com.discerned.purple.patient.Patient;
import com.discerned.purple.patient.PatientRepository;
import com.discerned.purple.patient.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/diagnoses")
public class DiagnosedController {
    private final DiagnosedService diagnosedService;
    private final PatientService patientService;

    public DiagnosedController(
            DiagnosedService diagnosedService,
            PatientService patientService) {
        this.diagnosedService = diagnosedService;
        this.patientService = patientService;
    }

    @GetMapping("/patient/{patientId}/diagnosed")
    public Set<Diagnosed> getDiagnoses(@PathVariable("patientId") UUID patientId) {
        Optional<Patient> patient = patientService.findPatientById(patientId);
        if (patient.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        } else {
            return patient.get().getDiagnoses();
        }
    }

    @PostMapping("/patient/{patientId}/diagnosed/save")
    public Diagnosed saveDiagnosed(@PathVariable("patientId") UUID patientId, @RequestBody Diagnosed diagnosed) {
        diagnosed.setPatient(patientId);
        diagnosedService.saveDiagnosed(diagnosed);
        return diagnosed;
    }
}
