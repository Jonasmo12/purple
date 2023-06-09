package com.discerned.purple.medication;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "medication")
@Getter
@Setter
@NoArgsConstructor
public class Medication {
    @javax.persistence.Id
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
    private UUID patient;
}
