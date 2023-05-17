package com.discerned.purple.security.jwt;

import com.discerned.purple.patient.Patient;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtGeneratorImpl implements JwtGeneratorInterface{
    @Value("secret")
    private String secret;
    @Value("Login Success")
    private String message;
    @Override
    public Map<String, String> generateToken(Patient patient) {
        String jwtToken="";
        jwtToken = Jwts.builder().setSubject(patient.getUsername()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secret").compact();
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        jwtTokenGen.put("message", message);
        return jwtTokenGen;
    }
}
