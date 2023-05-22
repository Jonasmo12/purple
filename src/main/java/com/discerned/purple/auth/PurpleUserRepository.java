package com.discerned.purple.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurpleUserRepository extends JpaRepository<PurpleUser, UUID> {
    Optional<PurpleUser> findByUsername(String username);
}
