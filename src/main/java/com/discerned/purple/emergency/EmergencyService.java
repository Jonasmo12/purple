package com.discerned.discerneded.emergency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmergencyService {
    @Autowired
    private final EmergencyRepository emergencyRepository;

    public EmergencyService(EmergencyRepository emergencyRepository) {
        this.emergencyRepository = emergencyRepository;
    }

    public void saveEmergency(Emergency emergency) {
        emergencyRepository.save(emergency);
    }

    public void findEmergencyById(Long emergencyId) {
        emergencyRepository.findById(emergencyId);
    }
}
