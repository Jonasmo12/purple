//package com.discerned.purple.patient;
//
//import com.discerned.purple.allergy.Allergy;
//import com.discerned.purple.allergy.AllergyRepository;
//import org.hibernate.id.UUIDGenerator;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.UUID;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class PatientTest {
//    @Autowired
//    private PatientRepository patientRepository;
//    @Autowired
//    private AllergyRepository allergyRepository;
//
//    @Test
//    void shouldCreatePatient() {
//        UUID patientId = UUID.randomUUID();
//        LocalDate dob = LocalDate.of(1993, 12, 1);
//        Integer age = LocalDate.now().getYear() - dob.getYear();
//        //given
//        Patient patient = new Patient(
//                "ROLE_USER",
//                "Male",
//                dob,
//                age,
//                "jonas@email.com",
//                "password",
//                "0659157229"
//
//        );
//        patientRepository.save(patient);
//        //when
//        String userExists = String.valueOf(patientRepository.findByUsername("jonas@email.com"));
//        //then
//
//        assertThat(userExists).isEqualTo(userExists);
//        assertThat(patient.getAge()).isEqualTo(30);
//        Set<Allergy> allergies = new HashSet<>();
//        assertThat(patient.getAllergies()).isEqualTo(allergies);
//    }
//
//
////    @Test
////    void createAllergies() {
////        UUID patientID = UUID.randomUUID();
////        Patient patient = new Patient(
////                patientID,
////                "bango@email.com",
////                "0659157229"
////        );
////        patientRepository.save(patient);
////        LocalDate diagnosedDate = LocalDate.of(2018, 12, 15);
////        Allergy allergy = new Allergy(
////                "Nuts",
////                diagnosedDate
////        );
////        allergyRepository.save(allergy);
////
////
////        allergy.setPatient(patient.getId());
////    }
//
//
//
//}