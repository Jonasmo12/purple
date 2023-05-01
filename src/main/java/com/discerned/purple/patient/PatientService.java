package com.discerned.purple.patient;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return patientRepository.findByUsername(username);
//    }
    public void createQRCode() {

    }

    public void createPatientId(Patient patient) {
        UUID patientId = UUID.randomUUID();
        if (patientRepository.findByPatientID(patientId).isEmpty()) {
            patient.setPatientID(patientId);
        } else {

        }
    }

    public void calculateAge(Patient patient) {
        Integer dob = LocalDate.now().getYear() - patient.getDob().getYear();
        patient.setAge(dob);
    }

    @Transactional
    public String signUpPatient(Patient patient) {
//        String encodedPassword = bCryptPasswordEncoder.encode(patient.getPassword());
//        patient.setPassword(encodedPassword);
//
//        UUID patientID = UUID.randomUUID();
//        patient.setPatientID(patientID);

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
        return "Its working";
    }

    public void findPatientById(Long id) {
        patientRepository.findById(id);
    }
}
