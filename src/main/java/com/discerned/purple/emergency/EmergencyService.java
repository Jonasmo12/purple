package com.discerned.purple.emergency;

import com.discerned.purple.patient.Patient;
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

    public Long findEmergencyById(Long emergencyId) {
        emergencyRepository.findById(emergencyId);
        return emergencyId;
    }

    public void deleteEmergencyContact(Long emergency) {
        emergencyRepository.deleteById(emergency);
    }
}
