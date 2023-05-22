package com.discerned.purple.adminuser;

import com.discerned.purple.auth.AuthenticationResponse;
import com.discerned.purple.auth.PurpleUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
