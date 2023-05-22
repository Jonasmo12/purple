package com.discerned.purple.adminuser;

import com.discerned.purple.auth.AuthenticationResponse;
import com.discerned.purple.auth.PurpleUserService;
import com.discerned.purple.patient.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/administration/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserService adminUserService;
    private final PurpleUserService purpleUserService;

    @PostMapping("/register")
    public AdminUser adminUserRegistration(@RequestBody AdminUser adminUser) {
        return adminUserService.createAdmin(adminUser);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> adminUserAuthentication(@RequestBody AdminUser adminUser) {
        return ResponseEntity.ok(purpleUserService.authenticate(adminUser));
    }

    @GetMapping("/patient/{patientId}")
    public Patient adminGetPatient(@PathVariable("patientId") UUID patientId) {
            return adminUserService.getPatient(patientId);
    }
}
