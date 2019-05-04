package com.cjss.model.employee;

import com.cjss.model.enums.Skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable {

    private String email;
    private String password;

    private long id;
    private String name;
    private String education;
    private String experience;
    private String hobbies;
    private String other;
    private String birthDate;
    private List<Skill> skills;

    {
        education = "";
        experience = "";
        hobbies = "";
        other = "";
        birthDate = "";
        skills = new ArrayList<>();
    }
    public Employee(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        if (hobbies == null) {
            this.hobbies = "";
        } else {
            this.hobbies = hobbies;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        if (other == null) {
            this.other = "";
        } else {
            this.other = other;
        }
    }

    public String getPassword() {
        return password;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        if (education == null) {
            this.education = "";
        } else {
            this.education = education;
        }
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        if (experience == null) {
            this.experience = "";
        } else {
            this.experience = experience;
        }
    }

}
