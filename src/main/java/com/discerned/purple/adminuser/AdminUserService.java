package com.discerned.purple.adminuser;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    private final AdminUserRepository adminUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminUserService(
            AdminUserRepository adminUserRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.adminUserRepository = adminUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public AdminUser createAdmin(AdminUser adminUser) {
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
}
