package com.cjss.web;

import com.cjss.model.employee.Employee;
import com.cjss.model.employee.EmployeeDao;
import com.cjss.model.employee.MySqlEmployeeDao;
import com.cjss.utils.EmployeeService;
import com.cjss.utils.SkillsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeEditProfilePageServlet extends HttpServlet {

    private static final String EMPLOYEE_KEY = "employee";
    private static final String CURRENT_USER_KEY = "current user";
    private static final String SKILLS_KEY = "skills";

    private final SkillsService skillsService = SkillsService.getInstance();
    private final EmployeeService employeeService = EmployeeService.getInstance();
    private final EmployeeDao employeeDao = MySqlEmployeeDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Employee employee = (Employee) req.getSession().getAttribute(CURRENT_USER_KEY);
            req.setAttribute(EMPLOYEE_KEY, employee);
            req.setAttribute(SKILLS_KEY, skillsService.getSkillsString(employee.getSkills()));
            req.getRequestDispatcher("/WEB-INF/pages/employeeProfileEdit.jsp").forward(req, resp);
        } catch (ClassCastException e) {
            resp.sendRedirect("registration-employee");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Employee employee = (Employee) req.getSession().getAttribute(CURRENT_USER_KEY);
            employee.setName(req.getParameter("name"));
            employee = employeeService.fillEmployee(employee, req);
            req.setAttribute(EMPLOYEE_KEY, employee);
            employeeDao.updateEmployee(employee);
            req.setAttribute(SKILLS_KEY, skillsService.getSkillsString(employee.getSkills()));
            req.getRequestDispatcher("/WEB-INF/pages/employeeProfile.jsp").forward(req, resp);
        } catch (ClassCastException e) {
            resp.sendRedirect("registration-employee");
        }
    }

}
