package com.discerned.purple.patient;

import com.discerned.purple.security.jwt.JwtService;
import com.discerned.purple.security.token.Token;
import com.discerned.purple.security.token.TokenRepository;
import com.discerned.purple.security.token.TokenType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService implements UserDetailsService {
    private final PatientRepository patientRepository;
    private final TokenRepository tokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public PatientService(
            @Lazy PatientRepository patientRepository,
            @Lazy TokenRepository tokenRepository,
            @Lazy BCryptPasswordEncoder bCryptPasswordEncoder,
            @Lazy JwtService jwtService,
            @Lazy AuthenticationManager authenticationManager
    ) {
        this.patientRepository = patientRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return patientRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("email %s not found", username)));
    }

    public void createQRCode() {}

    public AuthenticationResponse signUpPatient(Patient patient) {
        boolean userExists = patientRepository.findByUsername(patient.getUsername()).isPresent();

        if (userExists) {
            throw new IllegalStateException("username has been taken");
        } else {
            String encodedPassword = bCryptPasswordEncoder.encode(patient.getPassword());
            patient.setPassword(encodedPassword);

            boolean enableAccount = true;
            patient.setEnabled(enableAccount);

            UUID patientID = UUID.randomUUID();
            patient.setPatientID(patientID);

            Integer calculateAge = LocalDate.now().getYear() - patient.getDob().getYear();
            patient.setAge(calculateAge);

            var registrationRequest = new Patient(
                    patient.getPatientID(),
                    "ROLE_USER",
                    patient.getGender(),
                    patient.getDob(),
                    patient.getAge(),
                    patient.getUsername(),
                    patient.getPassword(),
                    patient.getPhone()
            );
            var savedPatient = patientRepository.save(registrationRequest);
            var jwtToken = jwtService.generateToken(registrationRequest);
            var refreshToken = jwtService.generateRefreshToken(registrationRequest);
            saveUserToken(savedPatient, jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }
    }

    public boolean existByPatientID(UUID patientID) {
        return patientRepository.existsByPatientID(patientID);
    }

    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public AuthenticationResponse authenticate(Patient patient) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        patient.getUsername(),
                        patient.getPassword()
                )
        );
        var user = patientRepository.findByUsername(patient.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(Patient patient, String jwtToken) {
        var token = Token.builder()
                .patient(patient)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Patient patient) {
        var validUserTokens = tokenRepository.findAllValidTokenByPatient(patient.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.patientRepository.findByUsername(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
