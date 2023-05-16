package com.discerned.purple.allergy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Allergy")
@Table(name = "allergy")
@Getter
@Setter
@NoArgsConstructor
public class Allergy {
    @Id
    @SequenceGenerator(
            name = "allergy_sequence",
            sequenceName = "allergy_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "allergy_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long Id;
    @Column(name = "patient_id")
    private Long patient;
    @Column(nullable = false)
    private String name;
    @Column(name = "diagnosed_date")
    private LocalDate diagnosedDate;
    private LocalDate CreatedDate = LocalDate.now();
    private boolean verified = false;

    public Allergy(String name, LocalDate diagnosedDate) {
        this.name = name;
        this.diagnosedDate = diagnosedDate;
    }

}