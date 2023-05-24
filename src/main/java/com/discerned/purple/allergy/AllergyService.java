package com.discerned.purple.allergy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Allergy save(Allergy allergy) {
        return allergyRepository.save(allergy);
    }

    public Allergy update(Long id, Allergy allergy) {
        allergyRepository.findById(id).ifPresent(savedAllergy -> {
            savedAllergy.setName(allergy.getName());
            savedAllergy.setDateDiagnosed(allergy.getDateDiagnosed());
            savedAllergy.setSymptoms(allergy.getSymptoms());
            savedAllergy.setSeverityOfReaction(allergy.getSeverityOfReaction());
            savedAllergy.setWhatToAvoid(allergy.getWhatToAvoid());
            allergyRepository.save(savedAllergy);
        });
        return allergy;
    }

    public Allergy findById(Long allergyId) {
        return allergyRepository.findById(allergyId)
                .orElseThrow(() -> new IllegalStateException("allergy not found"));
    }


}
