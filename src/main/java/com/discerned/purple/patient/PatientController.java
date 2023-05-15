package com.discerned.purple.patient;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@RestController
@CrossOrigin
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;
    private PatientService patientService;


//    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @GetMapping("/login")
    public String login(){
        return "login";
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/patient/{id}")
    public Optional<Patient> patient(@PathVariable("id") Long id) {
        return patientService.findPatientById(id);
    }

    @PostMapping("/registration")
    public Patient savePatient(@RequestBody Patient patient) {
        patientService.signUpPatient(patient);
        return patient;
    }
}
