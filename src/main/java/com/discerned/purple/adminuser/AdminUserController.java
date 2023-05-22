package com.discerned.purple.adminuser;

import com.discerned.purple.auth.AuthenticationResponse;
import com.discerned.purple.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserService adminUserService;
    private final UserService userService;

    @PostMapping("/registration_admin")
    public AdminUser adminUserRegistration(@RequestBody AdminUser adminUser) {
        return adminUserService.createAdmin(adminUser);
    }

    @PostMapping("/authenticate_admin")
    public ResponseEntity<AuthenticationResponse> adminUserAuthentication(AdminUser adminUser) {
        return ResponseEntity.ok(userService.authenticate(adminUser));
    }
}
