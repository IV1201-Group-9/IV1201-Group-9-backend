package com.iv1201.recapp.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * POJO for Competence Profile.
 */
@Entity
@Table()
public class CompetenceProfile {
    @Id
    @SequenceGenerator(
            name = "competence_profile_sequence",
            sequenceName = "competence_profile_sequence",
            allocationSize = 1,
            initialValue=7000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "competence_profile_sequence"
    )
    @Column(
            name = "competence_profile_id",
            updatable = false
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_id_person")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_id_competence")
    private Competence competence;

    @Column(
            name = "years_of_experience",
            columnDefinition = "NUMERIC"
    )
    private BigDecimal yearsOfExperience;

    public CompetenceProfile() {
    }

    public CompetenceProfile(User user,
                             Competence competence,
                             BigDecimal yearsOfExperience) {
        this.user = user;
        this.competence = competence;
        this.yearsOfExperience = yearsOfExperience;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public BigDecimal getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(BigDecimal yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

}
