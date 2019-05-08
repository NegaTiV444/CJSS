package com.cjss.model.vacancy;

import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.NotFoundException;

import java.util.List;

public interface VacancyDao {

    Vacancy getVacancy(long id) throws NotFoundException;

    List<Vacancy> findVacancy();

    List<Vacancy> findVacancy(String query);

    List<Vacancy> findVacancyByCompany(String companyName);

    List<Vacancy> findVacancy(List<Skill> skills);

    void addVacancy(Vacancy vacancy);

    void deleteVacancy(long id) throws NotFoundException;

}
