package com.discerned.purple.security.token;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, UUID> {

    @Query(value = "select t from Token t inner join PurpleUser u \n" +
                   "on t.id = u.id \n" +
                   "where u.id = :id and (t.expired = false or t.revoked = false) \n")
    List<Token> findAllValidTokenByUser(UUID id);

    Optional<Token> findByToken(String token);
}
