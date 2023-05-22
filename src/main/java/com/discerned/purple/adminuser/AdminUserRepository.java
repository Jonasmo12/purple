package com.discerned.purple.adminuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, UUID> {
}
