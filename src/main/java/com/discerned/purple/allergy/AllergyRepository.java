package com.discerned.purple.allergy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {
}