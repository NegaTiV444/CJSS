package com.cjss.web;

import com.cjss.model.employee.Employee;
import com.cjss.model.employee.EmployeeDao;
import com.cjss.model.employee.MySqlEmployeeDao;
import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.UserNotFoundException;
import com.cjss.utils.HashService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EmployeeProfilePageServlet extends HttpServlet {

    private HashService hashService = HashService.getInstance();

    private EmployeeDao employeeDao = MySqlEmployeeDao.newInstance();

    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private static final String NAME_KEY = "name";
    private static final String EDUCATION_KEY = "education";
    private static final String EXPERIENCE_KEY = "experience";
    private static final String SKILLS_KEY = "skills";
    private static final String HOBBIES_KEY = "hobbies";
    private static final String BIRTH_DATE_KEY = "birthDate";
    private static final String OTHER_KEY = "other";
    private static final String IN_SEARCH_KEY = "inSearch";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String)session.getAttribute(EMAIL_KEY);
        String password = (String)session.getAttribute(PASSWORD_KEY);
        if ((email != null) && (password != null)){

            Employee employee = null;
            try {
                employee = employeeDao.getEmployee(email);
            } catch (UserNotFoundException e){
                resp.sendRedirect("registration&loginMsg=wrong.email.error");
            }
            if (employee.getPassword().equals(password)){
                req.setAttribute(EMAIL_KEY, employee.getEmail());
                req.setAttribute(NAME_KEY, employee.getName());
                req.setAttribute(BIRTH_DATE_KEY, employee.getBirthDate());
                req.setAttribute(EDUCATION_KEY, employee.getEducation());
                req.setAttribute(EXPERIENCE_KEY, employee.getExperience());
                req.setAttribute(SKILLS_KEY, getSkillsString(employee.getSkills()));
                req.setAttribute(HOBBIES_KEY, employee.getHobbies());
                req.setAttribute(OTHER_KEY, employee.getOther());
                req.getRequestDispatcher("/WEB-INF/pages/employeeProfile.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("registration?loginMsg=wrong.password.error");
            }
        } else {
            resp.sendRedirect("registration");
        }
    }

    /*
        POST == LOGIN
    */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(EMAIL_KEY);
        String password = req.getParameter(PASSWORD_KEY);
        HttpSession session = req.getSession();
        session.setAttribute(EMAIL_KEY, email);
        session.setAttribute(PASSWORD_KEY, hashService.getHashAsString(password));
        doGet(req, resp);
    }

    private String getSkillsString(List<Skill> skills){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < skills.size(); i++){
            result.append(skills.get(i).toString() + " ");
        }
        return result.toString();
    }
}
