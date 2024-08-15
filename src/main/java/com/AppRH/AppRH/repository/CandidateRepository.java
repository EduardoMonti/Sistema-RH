package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.models.Candidate;
import com.AppRH.AppRH.models.Vacancy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidateRepository extends CrudRepository<Candidate, String> {

    Iterable<Candidate> findByVacancy(Vacancy vacancy);

    Candidate  findByRg(String rg);

    Candidate findById(long id);

    List<Candidate> findByCandidateName(String candidateName);

}
