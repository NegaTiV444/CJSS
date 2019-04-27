package com.cjss.web;

import com.cjss.model.employee.Employee;
import com.cjss.model.employee.EmployeeDao;
import com.cjss.model.employee.MySqlEmployeeDao;
import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.NotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EmployeeProfilePageServlet extends HttpServlet {

    private EmployeeDao employeeDao = MySqlEmployeeDao.newInstance();

    private static final String EMPLOYEE_KEY = "employee";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private static final String SKILLS_KEY = "skills";



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String)session.getAttribute(EMAIL_KEY);
        String password = (String)session.getAttribute(PASSWORD_KEY);
        if ((email != null) && (password != null)){

            Employee employee = null;
            try {
                employee = employeeDao.getEmployee(email);
            } catch (NotFoundException e){
                resp.sendRedirect("registration-employee?loginMsg=wrong.email.error");
                return;
            }
            if (employee.getPassword().equals(password)){
                req.setAttribute(EMPLOYEE_KEY, employee);
                req.setAttribute(SKILLS_KEY, getSkillsString(employee.getSkills()));
                req.getRequestDispatcher("/WEB-INF/pages/employeeProfile.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("registration-employee?loginMsg=wrong.password.error");
            }
        } else {
            resp.sendRedirect("registration-employee");
        }
    }

    private String getSkillsString(List<Skill> skills){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < skills.size(); i++){
            result.append(skills.get(i).toString() + " ");
        }
        return result.toString();
    }
}
