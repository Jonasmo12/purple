package com.discerned.purple.allergy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllergyService {
    public final AllergyRepository allergyRepository;

    public AllergyService(AllergyRepository allergyRepository) {
        this.allergyRepository = allergyRepository;
    }

    public List<Allergy> getAllAllergies() {
        return allergyRepository.findAll();
    }

    public void deleteAllergy(Long id) {
        allergyRepository.deleteById(id);
    }

    public Allergy saveAllergy(Allergy allergy) {
        return allergyRepository.save(allergy);
    }

    public Allergy findById(Long allergyId) {
        return allergyRepository.findById(allergyId)
                .orElseThrow(() -> new IllegalStateException("allergy not found"));
    }


}
