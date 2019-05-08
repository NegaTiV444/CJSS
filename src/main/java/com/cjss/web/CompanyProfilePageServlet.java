package com.cjss.web;

import com.cjss.model.company.Company;
import com.cjss.model.company.CompanyDao;
import com.cjss.model.company.MySqlCompanyDao;
import com.cjss.model.exceptions.NotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CompanyProfilePageServlet extends HttpServlet {

    private static final String CURRENT_USER_KEY = "current user";
    private static final String COMPANY_KEY = "company";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";

    private final CompanyDao companyDao = MySqlCompanyDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute(EMAIL_KEY);
        String password = (String) session.getAttribute(PASSWORD_KEY);
        if ((email != null) && (password != null)) {
            Company company = null;
            try {
                company = companyDao.getCompany(email);
            } catch (NotFoundException e) {
                resp.sendRedirect("../registration-company?loginMsg=wrong.email.error");
                return;
            }
            if (company.getPassword().equals(password)) {
                req.getSession().setAttribute(CURRENT_USER_KEY, company);
                req.setAttribute(COMPANY_KEY, company);
                req.getRequestDispatcher("/WEB-INF/pages/companyProfile.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("../registration-company?loginMsg=wrong.password.error");
            }
        } else {
            resp.sendRedirect("../registration-company");
        }
    }

}
