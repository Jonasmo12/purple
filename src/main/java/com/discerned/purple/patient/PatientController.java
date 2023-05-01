package com.discerned.purple.patient;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@AllArgsConstructor
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientService patientService;


//    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @GetMapping("/login")
    public String login(){

        return "login";
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/patient/{id}")
    public Patient patient(
            @PathVariable("id") Long id
    ) {
        try {
            Patient patient = patientRepository.findById(id).get();
            return patient;
        } catch (Exception exception) {
            return null;
        }
    }

//    @GetMapping("/patient")
//    public String patientHome(@AuthenticationPrincipal Patient patient, Model model) {
//        model.addAttribute("patient", patient);
//        model.addAttribute("emergency", patient.getEmergencies());
//        model.addAttribute("pageTitle", patient.getUsername().toUpperCase());
//        return "patient_home";
//    }


    @PostMapping("/registration")
    public Patient savePatient(@RequestBody Patient patient) {
        patientService.calculateAge(patient);
        patientService.createPatientId(patient);
        patientService.signUpPatient(patient);
        return patient;
    }
}
