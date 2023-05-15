package com.discerned.purple.emergency;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmergencyService {
    private final EmergencyRepository emergencyRepository;

    public EmergencyService(EmergencyRepository emergencyRepository) {
        this.emergencyRepository = emergencyRepository;
    }

    public void saveEmergency(Emergency emergency) {
        emergencyRepository.save(new Emergency(
                emergency.getFirstName(),
                emergency.getLastName(),
                emergency.getRelationship(),
                emergency.getPhone(),
                emergency.getPatient()
        ));
    }

    public Optional<Emergency> findEmergencyById(Long emergencyId) {
        return emergencyRepository.findById(emergencyId);

    }

    public boolean checkEmergencyExists(String emergencyPhone) {
        return emergencyRepository.existsByPhone(emergencyPhone);
    }

}
