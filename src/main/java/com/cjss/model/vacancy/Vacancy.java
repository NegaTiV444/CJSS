package com.cjss.model.vacancy;

import com.cjss.model.company.Company;
import com.cjss.model.enums.Skill;

import java.util.ArrayList;
import java.util.List;

public class Vacancy {

    private long id;
    private String position;
    private Company company;
    private String location;
    private String description;

    private List<Skill> requiredSkills = new ArrayList<>();

    public Vacancy() {
    }

    public Vacancy(String position, Company company, String location, String description) {
        this.position = position;
        this.company = company;
        this.location = location;
        this.description = description;
    }

    public List<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
