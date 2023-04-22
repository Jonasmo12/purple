package com.discerned.discerneded.diagnosed;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "diagnosed")
@Getter
@Setter
@NoArgsConstructor
public class Diagnosed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    private LocalDate diagnosedDate;
    private String description;
    private LocalDate CreatedDate = LocalDate.now();
    @Column(name = "patient_id")
    private Long patient;
    private boolean verified = false;
}
