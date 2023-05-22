package com.discerned.purple.emergency;

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
public class EmergencyController {
    private final EmergencyService emergencyService;
    private final PatientService patientService;

    public EmergencyController(
            EmergencyService emergencyService,
            PatientService patientService) {
        this.emergencyService = emergencyService;
        this.patientService = patientService;
    }

    @GetMapping("/patient/{patientId}/emergency")
    public Set<Emergency> getEmergencies(@PathVariable("patientId") UUID patientId) {
        Optional<Patient> patient = patientService.findPatientById(patientId);
        return patient.map(Patient::getEmergencies).orElse(null);
    }

    @PostMapping("/patient/{patientId}/emergency/save")
    public Emergency saveEmergency(
            @PathVariable("patientId") UUID patientId,
            @RequestBody Emergency emergency
    ) {
        boolean emergencyContact = emergencyService.checkEmergencyExists(emergency.getPhone());
        if (emergencyContact) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Emergency Contact already exists");
        } else {
            emergency.setPatient(patientId);
            emergencyService.saveEmergency(emergency);
            return emergency;
        }
    }

//    @PutMapping("/patient/{patientId}/emergency/{emergencyId}")
//    public Optional<Emergency> updateEmergency(
//            @RequestBody Emergency emergency,
//            @PathVariable("patientId") Long patientId,
//            @PathVariable("emergencyId") Long emergencyId
//    ) {
//        Optional<Emergency> updateEmergency = emergencyRepository.findById(emergencyId);
//        if (updateEmergency.isPresent()) {
//            updateEmergency.setFirstName(emergency.getFirstName());
//        }
//
//
//    }
}
