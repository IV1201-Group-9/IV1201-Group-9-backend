package com.iv1201.recapp.Models;

import jakarta.persistence.*;

@Entity
@Table()
public class Competence {
    @Id
    @SequenceGenerator(
            name = "competence_sequence",
            sequenceName = "competence_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "competence_sequence"
    )
    @Column(
            name = "competence_id",
            updatable = false
    )
    private long id;

    @Column(
            name = "competence_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String CompetenceName;

    public Competence() {
    }

    public Competence(String competenceName) {
        CompetenceName = competenceName;
    }

    public long getId() {
        return id;
    }


    public String getCompetenceName() {
        return CompetenceName;
    }

    public void setCompetenceName(String competenceName) {
        CompetenceName = competenceName;
    }

    @Override
    public String toString() {
        return "Competence{" +
                "id=" + id +
                ", CompetenceName='" + CompetenceName + '\'' +
                '}';
    }
}
