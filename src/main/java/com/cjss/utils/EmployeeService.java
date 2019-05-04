package com.cjss.utils;

import com.cjss.model.employee.Employee;
import com.cjss.model.employee.EmployeeDao;
import com.cjss.model.employee.MySqlEmployeeDao;
import com.cjss.model.exceptions.NotFoundException;

import javax.servlet.http.HttpServletRequest;

public class EmployeeService {

    private final SkillsService skillsService = SkillsService.getInstance();
    private final EmployeeDao employeeDao = MySqlEmployeeDao.getInstance();

    private EmployeeService() {
    }

    public static EmployeeService getInstance() {
        return SingletonHandler.INSTANCE;
    }

    public Employee fillEmployee(Employee employee, HttpServletRequest req) {
        employee.setEducation(req.getParameter("education"));
        employee.setBirthDate(req.getParameter("date"));
        employee.setOther(req.getParameter("other"));
        employee.setExperience(req.getParameter("experience"));
        employee.setHobbies(req.getParameter("hobbies"));
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
