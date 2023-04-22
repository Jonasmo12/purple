package com.discerned.purple.patient;

import com.discerned.discerneded.allergy.Allergy;
import com.discerned.discerneded.diagnosed.Diagnosed;
import com.discerned.discerneded.emergency.Emergency;
import com.discerned.discerneded.medication.Medication;
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
            unique = true,
            columnDefinition = "TEXT"
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
    private Set<Allergy> allergies;
    @OneToMany(
        cascade = CascadeType.ALL, 
        fetch = FetchType.EAGER
    )
    @JoinColumn(
        name = "patient_id", 
        referencedColumnName = "id"
    )
    private Set<Medication> medications;
    @OneToMany(
        cascade = CascadeType.ALL, 
        fetch = FetchType.EAGER
    )
    @JoinColumn(
        name = "patient_id", 
        referencedColumnName = "id"
    )
    private Set<Diagnosed> diagnoses;

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
            String username,
            String password,
            String phone
    ) {
        this.patientID = patientID;
        this.appUserRole = roleUser;
        this.gender = gender;
        this.dob = dob;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public Long getId() {
        return Id;
    }

    public UUID getPatientID() {
        return patientID;
    }

    public String getAppUserRole() {
        return appUserRole;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        Integer calculatedAge = LocalDate.now().getYear() - getDob().getYear();
        return calculatedAge;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAppUserRole(String appUserRole) {
        this.appUserRole = appUserRole;
    }

    public String getUsername() {
        return username;
    }

    public void setPatientID(UUID patientID) {
        this.patientID = patientID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
