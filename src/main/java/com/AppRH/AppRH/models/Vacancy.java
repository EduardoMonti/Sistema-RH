package com.AppRH.AppRH.models;

import com.AppRH.AppRH.models.Candidate;
import jakarta.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_vacancy")
public class Vacancy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Utilize a estratégia correta
    @Column(name = "code")
    private Long code;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String data;

    @NotEmpty
    private String salary;

    @OneToMany(mappedBy = "vacancy", cascade = CascadeType.REMOVE)
    private List<Candidate> candidates;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public @NotEmpty String getName() {
        return name;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public @NotEmpty String getDescription() {
        return description;
    }

    public void setDescription(@NotEmpty String description) {
        this.description = description;
    }

    public @NotEmpty String getData() {
        return data;
    }

    public void setData(@NotEmpty String data) {
        this.data = data;
    }

    public @NotEmpty String getSalary() {
        return salary;
    }

    public void setSalary(@NotEmpty String salary) {
        this.salary = salary;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
