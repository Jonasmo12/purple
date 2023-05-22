package com.discerned.purple.adminuser;

import com.discerned.purple.patient.Patient;
import com.discerned.purple.patient.PatientRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdminUserService {
    private final AdminUserRepository adminUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PatientRepository patientRepository;

    public AdminUserService(
            AdminUserRepository adminUserRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            PatientRepository patientRepository
    ) {
        this.adminUserRepository = adminUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.patientRepository = patientRepository;
    }

    public AdminUser createAdmin(AdminUser adminUser) {
        if (adminUserRepository.findAdminUserByUsername(adminUser.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }
        String encryptedPassword = bCryptPasswordEncoder.encode(adminUser.getPassword());
        adminUser.setPassword(encryptedPassword);

        return adminUserRepository.save(
                new AdminUser(
                    "ROLE_ADMIN",
                    adminUser.getFirstName(),
                    adminUser.getLastName(),
                    adminUser.getTitle(),
                    adminUser.getUsername(),
                    adminUser.getPassword()
        ));
    }

    public Patient getPatient(UUID patientId) throws UsernameNotFoundException {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("%s does not exist", patientId)));
    }
}
