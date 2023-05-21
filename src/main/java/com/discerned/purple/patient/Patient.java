package com.discerned.purple.patient;

import com.discerned.purple.allergy.Allergy;
import com.discerned.purple.auth.User;
import com.discerned.purple.diagnosed.Diagnosed;
import com.discerned.purple.emergency.Emergency;
import com.discerned.purple.medication.Medication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "Patient")
@Getter
@Setter
@NoArgsConstructor
public class Patient extends User {
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
            name = "username",
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
    private Boolean locked = false;
    private Boolean enabled = true;

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
}
