package com.cjss.model.company;

import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.AlreadyRegisteredException;
import com.cjss.model.exceptions.NotFoundException;

import java.util.List;

public interface CompanyDao {

    List<Company> findCompanies();
    Company getCompany(String query) throws NotFoundException;
    void addCompany(Company company) throws AlreadyRegisteredException;
    void deleteCompany(String name) throws NotFoundException;
}
