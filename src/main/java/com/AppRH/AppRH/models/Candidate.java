package com.AppRH.AppRH.models;

import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "tb_candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String rg;

    @NotEmpty
    private String candidateName;

    @NotEmpty
    private String email;

    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public @NotEmpty String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(@NotEmpty String candidateName) {
        this.candidateName = candidateName;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty String email) {
        this.email = email;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }
}
