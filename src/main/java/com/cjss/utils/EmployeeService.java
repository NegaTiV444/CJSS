package com.cjss.utils;

import com.cjss.model.employee.Employee;
import com.cjss.model.employee.EmployeeDao;
import com.cjss.model.employee.MySqlEmployeeDao;
import com.cjss.model.exceptions.NotFoundException;

import javax.servlet.http.HttpServletRequest;

public class EmployeeService {

    private final String EDUCATION_KEY = "education";
    private final String DATE_KEY = "date";
    private final String OTHER_KEY = "other";
    private final String EXPERIENCE_KEY = "experience";
    private final String HOBBIES_KEY = "hobbies";

    private final SkillsService skillsService = SkillsService.getInstance();
    private final EmployeeDao employeeDao = MySqlEmployeeDao.getInstance();

    private EmployeeService() {
    }

    public static EmployeeService getInstance() {
        return SingletonHandler.INSTANCE;
    }

    public Employee fillEmployee(Employee employee, HttpServletRequest req) {
        employee.setEducation(req.getParameter(EDUCATION_KEY) == null ? null : req.getParameter(EDUCATION_KEY).trim());
        employee.setBirthDate(req.getParameter(DATE_KEY));
        employee.setOther(req.getParameter(OTHER_KEY)  == null ? null : req.getParameter(OTHER_KEY).trim());
        employee.setExperience(req.getParameter(EXPERIENCE_KEY)  == null ? null : req.getParameter(EXPERIENCE_KEY).trim());
        employee.setHobbies(req.getParameter(HOBBIES_KEY)  == null ? null : req.getParameter(HOBBIES_KEY).trim());
        employee.getSkills().clear();
        employee.getSkills().addAll(skillsService.parseSkills(req.getParameterMap()));
        return employee;
    }

    public boolean checkEmployeeExists(Employee employee) {
        try {
            employeeDao.getEmployee(employee.getEmail());
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }

    private static class SingletonHandler {
        static final EmployeeService INSTANCE = new EmployeeService();
    }
}
