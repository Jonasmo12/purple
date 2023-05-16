package com.discerned.purple.patient;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService implements UserDetailsService {
    private final PatientRepository patientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PatientService(
            PatientRepository patientRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.patientRepository = patientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return patientRepository.findByUsername(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("email %s not found", email)));
    }

    public void createQRCode() {}

    public void signUpPatient(Patient patient) {
        boolean userExists = patientRepository.findByUsername(patient.getUsername()).isPresent();

        if (userExists) {
            throw new IllegalStateException("email has been taken");
        } else {
            String encodedPassword = bCryptPasswordEncoder.encode(patient.getPassword());
            patient.setPassword(encodedPassword);

            boolean enableAccount = true;
            patient.setEnabled(enableAccount);

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


    }

    public boolean existByPatientID(UUID patientID) {
        return patientRepository.existsByPatientID(patientID);
    }

    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }
}
