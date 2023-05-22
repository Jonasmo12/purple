package com.discerned.purple.adminuser;

import com.discerned.purple.auth.AuthenticationResponse;
import com.discerned.purple.auth.PurpleUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserService adminUserService;
    private final PurpleUserService purpleUserService;

    @PostMapping("/registration_admin")
    public AdminUser adminUserRegistration(@RequestBody AdminUser adminUser) {
        return adminUserService.createAdmin(adminUser);
    }

    @PostMapping("/authenticate_admin")
    public ResponseEntity<AuthenticationResponse> adminUserAuthentication(@RequestBody AdminUser adminUser) {
        return ResponseEntity.ok(purpleUserService.authenticate(adminUser));
    }
}
