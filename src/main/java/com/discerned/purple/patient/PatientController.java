package com.discerned.purple.patient;


import com.discerned.purple.auth.AuthenticationResponse;
import com.discerned.purple.auth.PurpleUserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final PurpleUserService purpleUserService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody Patient patient){
        return ResponseEntity.ok(purpleUserService.authenticate(patient));
    }

    @GetMapping("/patient")
    public UserDetails getPatient(Principal principal) {
        return purpleUserService.loadUserByUsername(principal.getName());
    }

    @PostMapping("/register")
    public ResponseEntity<Patient> register(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.signUpPatient(patient));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("logout successful");
    }
}
