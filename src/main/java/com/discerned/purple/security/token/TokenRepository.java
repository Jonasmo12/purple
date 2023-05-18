package com.discerned.purple.security.token;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = "select t from Token t inner join Patient u \n" +
                   "on t.id = u.Id \n" +
                   "where u.Id = :id and (t.expired = false or t.revoked = false) \n")
    List<Token> findAllValidTokenByPatient(Long id);

    Optional<Token> findByToken(String token);
}