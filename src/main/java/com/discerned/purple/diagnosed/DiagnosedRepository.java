package com.discerned.discerneded.diagnosed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosedRepository extends JpaRepository<Diagnosed, Long> {
}
