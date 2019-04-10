package com.cjss.model.vacancy;

import com.cjss.model.enums.Skill;

import java.util.HashSet;
import java.util.Set;

public class Vacancy {

    private String position;
    private String companyName;
    private String location;
    private String description;

    private Set<Skill> requiredSkills;

    public Set<Skill> getRequiredSkills() {
        return requiredSkills;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Vacancy() {
        requiredSkills = new HashSet<>();
    }

    public Vacancy(String position, String companyName, String location, String description) {
        this.position = position;
        this.companyName = companyName;
        this.location = location;
        this.description = description;
    }
}
