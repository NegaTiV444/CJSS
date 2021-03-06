package com.cjss.model.vacancy;

import com.cjss.model.enums.Skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vacancy implements Serializable {

    private long id;
    private String position;
    private String companyName;
    private String location;
    private String description;
    private String conditions;
    private List<Skill> requiredSkills = new ArrayList<>();

    public Vacancy() {
    }

    public Vacancy(String position, String companyName, String location, String description) {
        this.position = position;
        this.companyName = companyName;
        this.location = location;
        this.description = description;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
