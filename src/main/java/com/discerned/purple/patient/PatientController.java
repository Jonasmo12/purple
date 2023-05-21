package com.discerned.purple.patient;


import com.discerned.purple.auth.AuthenticationResponse;
import com.discerned.purple.auth.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController(value = "/api/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody Patient patient){
        return ResponseEntity.ok(userService.authenticate(patient));
    }

    @GetMapping("/patient")
    public UserDetails getPatient(Principal principal) {
        return userService.loadUserByUsername(principal.getName());
    }

    @PostMapping("/registration")
    public ResponseEntity<Patient> register(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.signUpPatient(patient));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("logout successful");
    }
}
