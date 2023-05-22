package com.discerned.purple.emergency;

import com.discerned.purple.patient.Patient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Emergency")
@Getter
@Setter
@NoArgsConstructor
public class Emergency {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private Long Id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String relationship;
    private String phone;
    @Column(name = "patient_id")
    private UUID patient;

    public Emergency(String firstName, String lastName, String relationship, String phone, UUID patient) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
        this.phone = phone;
        this.patient = patient;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

}
