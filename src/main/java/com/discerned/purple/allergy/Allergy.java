package com.discerned.purple.allergy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

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
    private Long id;
    @Column(name = "patient_id")
    private UUID patient;
    @Column(nullable = false)
    private String name;
    @Column(name = "date_diagnosed")
    private LocalDate dateDiagnosed;
    private String symptoms;
    @Column(name = "severity_of_reaction")
    private String severityOfReaction;
    @Column(name = "what_to_avoid")
    private String whatToAvoid;
    private LocalDate CreatedDate = LocalDate.now();

    public Allergy(
            String name,
            LocalDate dateDiagnosed,
            String symptoms,
            String severityOfReaction,
            String whatToAvoid
    ) {
        this.name = name;
        this.dateDiagnosed = dateDiagnosed;
        this.symptoms = symptoms;
        this.severityOfReaction = severityOfReaction;
        this.whatToAvoid = whatToAvoid;
    }

}
