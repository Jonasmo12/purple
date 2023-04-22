package com.discerned.discerneded.medication;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;

@Entity
@Table(name = "medication")
@Getter
@Setter
@NoArgsConstructor
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", updatable = false)
    private Long Id;
    @Column(name = "name")
    private String name;
    @Column(name = "reasons")
    private String reasons;
    private boolean prescribed;
    private LocalDate startDate;
    private LocalDate endDate;
    @Column(name = "patient_id")
    private Long patient;
}
