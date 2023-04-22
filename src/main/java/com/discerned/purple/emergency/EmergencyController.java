package com.discerned.discerneded.emergency;

import com.discerned.discerneded.patient.Patient;
import com.discerned.discerneded.patient.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class EmergencyController {
    private final EmergencyService emergencyService;
    private final PatientRepository patientRepository;

    public EmergencyController(EmergencyService emergencyService, PatientRepository patientRepository) {
        this.emergencyService = emergencyService;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patient/{patientId}/emergency")
    public String getEmergency(@PathVariable("patientId") Long patientId, Model model) {
        Patient patient = patientRepository.findById(patientId).get();

        model.addAttribute("emergency", new Emergency());
        model.addAttribute("patient", patient);

        return "emergency.html";
    }

    @PostMapping("/patient/{patientId}/emergency/save")
    public String saveEmergency(
            @PathVariable("patientId") Long patientId,
            @ModelAttribute Emergency emergency
    ) {
        Patient patient = patientRepository.findById(patientId).get();

        emergency.setPatient(patient.getId());
        emergencyService.saveEmergency(emergency);

        return "redirect:/patient";
    }
}
