package com.discerned.purple.patient;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/patient/{id}")
    public String patient(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Patient patient = patientRepository.findById(id).get();
            model.addAttribute("patient", patient);
            model.addAttribute("pageTitle", "Edit Patient(ID: " + id + ")");

            return "patient";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "patient";
        }
    }

//    @GetMapping("/patient")
//    public String patientHome(@AuthenticationPrincipal Patient patient, Model model) {
//        model.addAttribute("patient", patient);
//        model.addAttribute("emergency", patient.getEmergencies());
//        model.addAttribute("pageTitle", patient.getUsername().toUpperCase());
//        return "patient_home";
//    }
    @GetMapping("/patient/new")
    public String newPatient(Model model) {
        Patient patient = new Patient();

        model.addAttribute("patient", patient);
        model.addAttribute("pageTitle", "Create new Patient");

        return "patient_form";
    }

    @PostMapping("/patient/save")
    public String savePatient(@ModelAttribute Patient patient, RedirectAttributes redirectAttributes) {
        try {
            patientService.signUpPatient(patient);
            redirectAttributes.addFlashAttribute("message", "Patient Saved");
        } catch (Exception exception) {
            redirectAttributes.addAttribute("message", exception.getMessage());
        }
        return "redirect:/";
    }
}
