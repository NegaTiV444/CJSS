package com.cjss.model.vacancy;

import com.cjss.model.company.Company;
import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.NotFoundException;

import java.util.List;

public interface VacancyDao {

    Vacancy getVacancy(int id) throws NotFoundException;
    List<Vacancy> findVacancy();
    List<Vacancy> findVacancy(String query);
    List<Vacancy> findVacancy(Company company);
    List<Vacancy> findVacancy(List<Skill> skills);
    void addVacancy(Vacancy vacancy);
    void deleteVacancy(Vacancy vacancy);

}
