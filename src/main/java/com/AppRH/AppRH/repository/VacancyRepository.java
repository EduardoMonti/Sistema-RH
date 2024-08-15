package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.models.Vacancy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VacancyRepository extends CrudRepository<Vacancy, String> {
        Vacancy findByCode(long code);
        List<Vacancy> findByName(String name);
        }

