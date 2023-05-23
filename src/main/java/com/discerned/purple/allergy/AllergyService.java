package com.discerned.purple.allergy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllergyService {
    @Autowired
    public final AllergyRepository allergyRepository;

    public AllergyService(AllergyRepository allergyRepository) {
        this.allergyRepository = allergyRepository;
    }

    public List<Allergy> getAllAllergies() {
        return allergyRepository.findAll();
    }

    public Allergy deleteAllergy(Long allergyID){
       return allergyRepository.deleteById(allergyID);
    }

    public Allergy saveAllergy(Allergy allergy) {
        return allergyRepository.save(allergy);
    }

    public Allergy findById(Long allergyId) {
        return allergyRepository.findById(allergyId)
                .orElseThrow(() -> new IllegalStateException("allergy not found"));
    }


}
