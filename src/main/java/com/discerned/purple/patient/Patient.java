package com.discerned.purple.patient;

import com.discerned.purple.allergy.Allergy;
import com.discerned.purple.diagnosed.Diagnosed;
import com.discerned.purple.emergency.Emergency;
import com.discerned.purple.medication.Medication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "Patient")
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Long Id;
    @Column(
            updatable = false,
            name = "patientID",
            unique = true
    )
    private UUID patientID;
    private String appUserRole;
    private String gender;
    @Column(name = "date_of_birth")
    private LocalDate dob;
    private Integer age;
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String username;
    private String password;
    private String phone;
    @OneToMany(
        cascade = CascadeType.ALL, 
        fetch = FetchType.EAGER
    )
    @JoinColumn(
        name = "patient_id", 
        referencedColumnName = "id"
    )
    private Set<Emergency> emergencies = new HashSet<>();
    @OneToMany(
        cascade = CascadeType.ALL, 
        fetch = FetchType.EAGER
    )
    @JoinColumn(
        name = "patient_id", 
        referencedColumnName = "id"
    )
    private Set<Allergy> allergies = new HashSet<>();
    @OneToMany(
        cascade = CascadeType.ALL, 
        fetch = FetchType.EAGER
    )
    @JoinColumn(
        name = "patient_id", 
        referencedColumnName = "id"
    )
    private Set<Medication> medications = new HashSet<>();
    @OneToMany(
        cascade = CascadeType.ALL, 
        fetch = FetchType.EAGER
    )
    @JoinColumn(
        name = "patient_id", 
        referencedColumnName = "id"
    )
    private Set<Diagnosed> diagnoses = new HashSet<>();

    public Patient(
            String appUserRole,
            String username,
            String password
    ) {
        this.appUserRole = appUserRole;
        this.username = username;
        this.password = password;
    }

    public Patient(
            UUID patientID,
            String roleUser,
            String gender,
            LocalDate dob,
            Integer age,
            String username,
            String password,
            String phone
    ) {
        this.patientID = patientID;
        this.appUserRole = roleUser;
        this.gender = gender;
        this.dob = dob;
        this.age = age;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    Patient(UUID patientID, String username, String phone) {
        this.patientID = patientID;
        this.username = username;
        this.phone = phone;
    }

    public Set<Emergency> getEmergencies() {
        return emergencies;
    }

    public void setEmergencies(Set<Emergency> emergencies) {
        this.emergencies = emergencies;
    }

    public Set<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<Allergy> allergies) {
        this.allergies = allergies;
    }

    public Set<Diagnosed> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnosed> diagnoses) {
        this.diagnoses = diagnoses;
    }


//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Arrays.stream(this
//                        .getAppUserRole()
//                        .split(","))
//                .map(SimpleGrantedAuthority::new)
//                .toList();
//    }

}
