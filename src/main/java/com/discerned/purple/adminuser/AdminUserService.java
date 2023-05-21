package com.discerned.purple.adminuser;

import com.discerned.purple.auth.AuthenticationResponse;
import com.discerned.purple.patient.PatientService;
import com.discerned.purple.security.jwt.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    private final AdminUserRepository adminUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final PatientService patientService;

    public AdminUserService(AdminUserRepository adminUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtService jwtService, PatientService patientService) {
        this.adminUserRepository = adminUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
        this.patientService = patientService;
    }

    public AuthenticationResponse createAdmin(AdminUser adminUser) {
        String encryptedPassword = bCryptPasswordEncoder.encode(adminUser.getPassword());
        adminUser.setPassword(encryptedPassword);

        var registerAdmin = new AdminUser(
                "ROLE_ADMIN",
                adminUser.getFirstName(),
                adminUser.getLastName(),
                adminUser.getTitle(),
                adminUser.getUsername(),
                adminUser.getPassword()
        );
        var saveAdmin = adminUserRepository.save(registerAdmin);
        var accessToken = jwtService.generateToken(saveAdmin);
        var refreshToken = jwtService.generateRefreshToken(saveAdmin);
        saveUserToken(saveAdmin, accessToken);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void saveUserToken(AdminUser adminUser, String jwtService) {
    }

}
