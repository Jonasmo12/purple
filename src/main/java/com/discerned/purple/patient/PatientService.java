package com.discerned.purple.patient;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void createQRCode() {}

    public void signUpPatient(Patient patient) {
        UUID patientID = UUID.randomUUID();
        patient.setPatientID(patientID);

        Integer calculateAge = LocalDate.now().getYear() - patient.getDob().getYear();
        patient.setAge(calculateAge);

        patientRepository.save(
                new Patient(
                        patient.getPatientID(),
                        "ROLE_USER",
                        patient.getGender(),
                        patient.getDob(),
                        patient.getAge(),
                        patient.getUsername(),
                        patient.getPassword(),
                        patient.getPhone()
                ));
    }

    public boolean existByPatientID(UUID patientID) {
        return patientRepository.existsByPatientID(patientID);
    }

    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }
}
