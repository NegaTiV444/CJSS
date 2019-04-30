package com.cjss.web;

import com.cjss.model.company.CompanyDao;
import com.cjss.model.company.MySqlCompanyDao;
import com.cjss.model.employee.EmployeeDao;
import com.cjss.model.employee.MySqlEmployeeDao;
import com.cjss.model.exceptions.NotFoundException;
import com.cjss.utils.HashService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ProfileServlet extends HttpServlet {

    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";

    private final HashService hashService = HashService.getInstance();
    private final CompanyDao companyDao = MySqlCompanyDao.getInstance();
    private final EmployeeDao employeeDao = MySqlEmployeeDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute(EMAIL_KEY);
        String password = (String) session.getAttribute(PASSWORD_KEY);
        if ((email != null) && (password != null)) {
            try {
                employeeDao.getEmployee(email);
                resp.sendRedirect("profile/employee");
            } catch (NotFoundException e) {
                try {
                    companyDao.getCompany(email);
                    resp.sendRedirect("profile/company");
                }catch (NotFoundException ex){
                    resp.sendRedirect("registration-employee");
                }
            }
        } else {
            resp.sendRedirect("registration-employee");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(EMAIL_KEY);
        String password = req.getParameter(PASSWORD_KEY);
        HttpSession session = req.getSession();
        session.setAttribute(EMAIL_KEY, email);
        session.setAttribute(PASSWORD_KEY, hashService.getHashAsString(password));
        doGet(req, resp);
    }
}
