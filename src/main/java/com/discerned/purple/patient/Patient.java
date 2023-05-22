package com.discerned.purple.patient;

import com.discerned.purple.allergy.Allergy;
import com.discerned.purple.auth.PurpleUser;
import com.discerned.purple.diagnosed.Diagnosed;
import com.discerned.purple.emergency.Emergency;
import com.discerned.purple.medication.Medication;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Patient")
@Getter
@Setter
@NoArgsConstructor
public class Patient extends PurpleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private UUID id;
    private String role = "ROLE_USER";
    private String gender;
    @Column(name = "date_of_birth")
    private LocalDate dob;
    private Integer age;
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
            String role,
            String gender,
            LocalDate dob,
            Integer age,
            String username,
            String password,
            String phone
    ) {
        super(role, username, password);
        this.gender = gender;
        this.dob = dob;
        this.age = age;
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
