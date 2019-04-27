package com.cjss.web;

import com.cjss.model.employee.Employee;
import com.cjss.model.employee.EmployeeDao;
import com.cjss.model.employee.MySqlEmployeeDao;
import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.NotFoundException;
import com.cjss.utils.HashService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRegistrationPageServlet extends HttpServlet {

    private static final String THIS_EMAIL_IS_ALREADY_TAKEN_ERROR = "email.is.taken.error";

    private HashService hashService = HashService.getInstance();

    private EmployeeDao employeeDao = MySqlEmployeeDao.newInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/employeeRegistration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee newEmployee = getEmployeeFromRequest(req);
        if (checkEmployeeExists(newEmployee)) {
            resp.sendRedirect("registration?registerMsg=" + THIS_EMAIL_IS_ALREADY_TAKEN_ERROR);
        } else {
            employeeDao.addEmployee(newEmployee);
            HttpSession session = req.getSession();
            session.setAttribute("email", newEmployee.getEmail());
            session.setAttribute("password", hashService.getHashAsString(newEmployee.getPassword()));
            resp.sendRedirect("profile");
        }
    }

    private Employee getEmployeeFromRequest(HttpServletRequest req) {
        Employee employee = new Employee(req.getParameter("email"), req.getParameter("password"));
        String temp = req.getParameter("first_name");
        temp = temp + " " + req.getParameter("last_name");
        employee.setName(temp);
        temp = req.getParameter("education");
        employee.setEducation(temp);
        temp = req.getParameter("date");
        employee.setBirthDate(temp);
        temp = req.getParameter("other");
        employee.setOther(temp);
        temp = req.getParameter("experience");
        employee.setExperience(temp);
        employee.getSkills().addAll(parseSkills(req));
        return employee;
    }

    private boolean checkEmployeeExists(Employee employee) {
        try {
            employeeDao.getEmployee(employee.getEmail());
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }

    private List<Skill> parseSkills(HttpServletRequest request){
        List<Skill> result = new ArrayList<>();
        String tmp;
        final String SELECTED = "selected";
        for (Skill skill : Skill.values()){
            tmp = request.getParameter(skill.toString());
            if (tmp != null)
                result.add(skill);
        }
        return result;
    }
}
