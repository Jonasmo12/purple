package com.discerned.purple.security.jwt;

import com.discerned.purple.patient.Patient;

import java.util.Map;

public interface JwtGeneratorInterface {
    Map<String, String> generateToken(Patient patient);
}
