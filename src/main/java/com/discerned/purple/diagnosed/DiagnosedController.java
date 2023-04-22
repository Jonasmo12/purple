package com.discerned.discerneded.diagnosed;

import com.discerned.discerneded.patient.Patient;
import com.discerned.discerneded.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DiagnosedController {
    @Autowired
    private final DiagnosedService diagnosedService;
    @Autowired
    private final PatientRepository patientRepository;

    public DiagnosedController(DiagnosedService diagnosedService, PatientRepository patientRepository) {
        this.diagnosedService = diagnosedService;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patient/{patientId}/diagnosed")
    public String getDiagnosed(
            @PathVariable("patientId") Long patientId,
            Model model
    ) {
        Patient patient = patientRepository.findById(patientId).get();
        model.addAttribute("diagnosed", new Diagnosed());
        model.addAttribute("patient", patient);

        return "diagnosed.html";
    }

    @PostMapping("/patient/{patientId}/diagnosed/save")
    public String saveDiagnosed(
            @PathVariable("patientId") Long patientId,
            @ModelAttribute Diagnosed diagnosed
    ) {
        Patient patient = patientRepository.findById(patientId).get();
        diagnosed.setPatient(patient.getId());
        diagnosedService.saveDiagnosed(diagnosed);
        return "redirect:/patient";
    }
}
