package com.discerned.purple.emergency;

import com.discerned.purple.patient.Patient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private Long patient;

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

}
