package com.discerned.purple.patient;


import com.discerned.purple.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

//    @PostMapping("/login")
//    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody Patient patient){
//        return ResponseEntity.ok(patientService.authenticate(patient));
//    }
//
//    @GetMapping("/patient")
//    public UserDetails getPatient(Principal principal) {
//        return patientService.loadUserByUsername(principal.getName());
//    }
//
//    @PostMapping("/registration")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody Patient patient) {
//        return ResponseEntity.ok(patientService.signUpPatient(patient));
//    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("logout successful");
    }

//    @PostMapping("/refresh-token")
//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        patientService.refreshToken(request, response);
//    }
}
