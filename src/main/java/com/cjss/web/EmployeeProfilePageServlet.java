package com.cjss.web;

import com.cjss.model.employee.Employee;
import com.cjss.model.employee.EmployeeDao;
import com.cjss.model.employee.MySqlEmployeeDao;
import com.cjss.model.exceptions.NotFoundException;
import com.cjss.utils.SkillsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EmployeeProfilePageServlet extends HttpServlet {

    private static final String CURRENT_USER_KEY = "current user";
    private static final String EMPLOYEE_KEY = "employee";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private static final String SKILLS_KEY = "skills";

    private final SkillsService skillsService = SkillsService.getInstance();
    private final EmployeeDao employeeDao = MySqlEmployeeDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute(EMAIL_KEY);
        String password = (String) session.getAttribute(PASSWORD_KEY);
        if ((email != null) && (password != null)) {

            Employee employee = null;
            try {
                employee = employeeDao.getEmployee(email);
            } catch (NotFoundException e) {
                resp.sendRedirect("../registration-employee?loginMsg=wrong.email.error");
                return;
            }
            if (employee.getPassword().equals(password)) {
                req.getSession().setAttribute(CURRENT_USER_KEY, employee);
                req.setAttribute(EMPLOYEE_KEY, employee);
                req.setAttribute(SKILLS_KEY, skillsService.getSkillsString(employee.getSkills()));
                req.getRequestDispatcher("/WEB-INF/pages/employeeProfile.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("../registration-employee?loginMsg=wrong.password.error");
            }
        } else {
            resp.sendRedirect("../registration-employee");
        }
    }

}
