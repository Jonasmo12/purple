package com.discerned.purple.patient;

import com.discerned.purple.auth.AuthenticationResponse;
import com.discerned.purple.security.jwt.JwtService;
import com.discerned.purple.security.token.TokenRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PatientService(
            @Lazy PatientRepository patientRepository,
            @Lazy BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.patientRepository = patientRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void createQRCode() {}

    public Patient signUpPatient(Patient patient) {
        boolean userExists = patientRepository.findByUsername(patient.getUsername()).isPresent();

        if (userExists) {
            throw new IllegalStateException("username has been taken");
        } else {
            String encodedPassword = bCryptPasswordEncoder.encode(patient.getPassword());
            patient.setPassword(encodedPassword);

            Integer calculateAge = LocalDate.now().getYear() - patient.getDob().getYear();
            patient.setAge(calculateAge);

            return patientRepository.save(new Patient(
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
