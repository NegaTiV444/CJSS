package com.cjss.model.employee;

import com.cjss.model.enums.Skill;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private String email;
    private String password;

    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int inSearch = 1;

    private String education;
    private String experience;
    private String hobbies;
    private String other;
    private String birthDate;

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
        this.hobbies = hobbies;
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
        this.other = other;
    }

    private List<Skill> skills;

    private List<String> messages;

    public Employee() {
        education = "";
        experience = "";
        hobbies = "";
        other = "";
        birthDate = "";
        skills = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public Employee(String email, String password) {
        education = "";
        experience = "";
        hobbies = "";
        other = "";
        birthDate = "";
        this.email = email;
        this.password = password;
        skills = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int isInSearch() {
        return inSearch;
    }

    public void setInSearch(int inSearch) {
        this.inSearch = inSearch;
    }

}
