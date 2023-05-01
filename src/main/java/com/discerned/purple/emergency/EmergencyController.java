package com.discerned.purple.emergency;

import com.discerned.purple.patient.Patient;
import com.discerned.purple.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class EmergencyController {
    @Autowired
    private final EmergencyService emergencyService;
    @Autowired
    private final PatientRepository patientRepository;
    @Autowired
    private final EmergencyRepository emergencyRepository;

    public EmergencyController(EmergencyService emergencyService, PatientRepository patientRepository, EmergencyRepository emergencyRepository) {
        this.emergencyService = emergencyService;
        this.patientRepository = patientRepository;
        this.emergencyRepository = emergencyRepository;
    }


    @GetMapping("/patient/{patientId}/emergency")
    public Set<Emergency> getEmergencies(
            @PathVariable("patientId") Long patientId
    ) {
        Patient patient = patientRepository.findById(patientId).get();
        return patient.getEmergencies();
    }

    @PostMapping("/patient/{patientId}/emergency/save")
    public Emergency saveEmergency(
            @PathVariable("patientId") Long patientId,
            @RequestBody Emergency emergency
    ) {
        emergency.setPatient(patientId);
        emergencyService.saveEmergency(emergency);
        return emergency;
    }

    @DeleteMapping("patient/{patientId}/emergency/{emergencyId}/delete")
    public void deleteEmergency(
            @PathVariable("patientId") Long patientId,
            @PathVariable("emergencyId") Long emergencyId
    ) {
        Long emergencyContact = emergencyService.findEmergencyById(emergencyId);
        emergencyService.deleteEmergencyContact(emergencyContact);
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
