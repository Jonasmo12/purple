package com.discerned.purple.diagnosed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosedService {
    @Autowired
    private final DiagnosedRepository diagnosedRepository;

    public DiagnosedService(DiagnosedRepository diagnosedRepository) {
        this.diagnosedRepository = diagnosedRepository;
    }

    public void saveDiagnosed(Diagnosed diagnosed) {
        diagnosedRepository.save(diagnosed);
    }
}
