package com.cjss.web;

import com.cjss.model.employee.Employee;
import com.cjss.model.employee.EmployeeDao;
import com.cjss.model.employee.MySqlEmployeeDao;
import com.cjss.utils.SkillsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeePageServlet extends HttpServlet {

    private static final String EMPLOYEE_KEY = "employee";
    private static final String SKILLS_KEY = "skills";

    private final SkillsService skillsService = SkillsService.getInstance();
    private final EmployeeDao employeeDao = MySqlEmployeeDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Employee employee = employeeDao.getEmployee(Integer.parseInt(req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/') + 1)));
        req.setAttribute(EMPLOYEE_KEY, employee);
        req.getRequestDispatcher("/WEB-INF/pages/employee.jsp").forward(req, resp);
    }
}
