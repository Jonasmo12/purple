package com.discerned.purple.patient;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L)
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody Patient patient){
        return ResponseEntity.ok(patientService.authenticate(patient));
    }

    @GetMapping("/patient")
    @ResponseBody
    public UserDetails getPatient(Principal principal) {
        return patientService.loadUserByUsername(principal.getName());
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.signUpPatient(patient));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        patientService.refreshToken(request, response);
    }
}
