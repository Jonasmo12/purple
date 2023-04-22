package com.discerned.discerneded.allergy;

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

    public void deleteAllergy(Long allergyID){
        allergyRepository.deleteById(allergyID);
    }

    public void saveAllergy(Allergy allergy) {
        allergyRepository.save(allergy);
    }
}
