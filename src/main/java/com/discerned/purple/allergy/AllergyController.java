package com.discerned.discerneded.allergy;

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
public class AllergyController {
    @Autowired
    private final AllergyService allergyService;
    @Autowired
    private final PatientRepository patientRepository;

    public AllergyController(AllergyService allergyService, PatientRepository patientRepository) {
        this.allergyService = allergyService;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patient/{patientId}/allergy")
    public String getAllergy(
            @PathVariable("patientId") Long patientId,
            Model model
    ) {
        Patient patient = patientRepository.findById(patientId).get();
        model.addAttribute("allergy", new Allergy());
        model.addAttribute("patient", patient);

        return "allergy.html";
    }

    @PostMapping("/patient/{patientId}/allergy/save")
    public String saveAllergy(
            @PathVariable("patientId") Long patientId,
            @ModelAttribute Allergy allergy
    ) {
        Patient patient = patientRepository.findById(patientId).get();
        allergy.setPatient(patient.getId());
        allergyService.saveAllergy(allergy);
        return "redirect:/patient";
    }
}
