package com.discerned.discerneded.medication;

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
public class MedicationController {
    @Autowired
    private final MedicationService medicationService;
    @Autowired
    private final PatientRepository patientRepository;

    public MedicationController(MedicationService medicationService, PatientRepository patientRepository) {
        this.medicationService = medicationService;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patient/{patientId}/medication")
    public String getMedicationForm(@PathVariable("patientId") Long patientId, Model model) {
        Patient patient = patientRepository.findById(patientId).get();
        model.addAttribute("patient", patient);
        model.addAttribute("medication", new Medication());
        return "medication.html";
    }

    @PostMapping("/patient/{patientId}/medication/save")
    public String saveMedication(@PathVariable("patientId") Long patientId, @ModelAttribute Medication medication) {
        Patient patient = patientRepository.findById(patientId).get();
        medication.setPatient(patient.getId());
        medicationService.saveMedication(medication);
        return "redirect:/patient";
    }
}
