package com.discerned.purple.diagnosed;

import com.discerned.purple.patient.Patient;
import com.discerned.purple.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class DiagnosedController {
    @Autowired
    private final DiagnosedService diagnosedService;
    @Autowired
    private final PatientRepository patientRepository;

    public DiagnosedController(
            DiagnosedService diagnosedService,
            PatientRepository patientRepository
    ) {
        this.diagnosedService = diagnosedService;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patient/{patientId}/diagnosed")
    public Set<Diagnosed> getDiagnoses(
            @PathVariable("patientId") Long patientId
    ) {
        Patient patient = patientRepository.findById(patientId).get();
        return patient.getDiagnoses();
    }

    @PostMapping("/patient/{patientId}/diagnosed/save")
    public Diagnosed saveDiagnosed(
            @PathVariable("patientId") Long patientId,
            @RequestBody Diagnosed diagnosed
    ) {
        diagnosed.setPatient(patientId);
        diagnosedService.saveDiagnosed(diagnosed);
        return diagnosed;
    }
}
