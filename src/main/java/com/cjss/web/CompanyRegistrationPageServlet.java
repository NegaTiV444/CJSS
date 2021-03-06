package com.cjss.web;

import com.cjss.model.company.Company;
import com.cjss.model.company.CompanyDao;
import com.cjss.model.company.MySqlCompanyDao;
import com.cjss.model.exceptions.AlreadyRegisteredException;
import com.cjss.utils.CompanyService;
import com.cjss.utils.HashService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CompanyRegistrationPageServlet extends HttpServlet {

    private static final String THIS_NAME_OR_EMAIL_IS_ALREADY_TAKEN_ERROR = "name.or.email.is.taken.error";

    private final HashService hashService = HashService.getInstance();
    private final CompanyDao companyDao = MySqlCompanyDao.getInstance();
    private final CompanyService companyService = CompanyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/companyRegistration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Company newCompany = getCompanyFromRequest(req);
        try {
            companyDao.addCompany(newCompany);
            HttpSession session = req.getSession();
            session.setAttribute("email", newCompany.getEmail());
            session.setAttribute("password", hashService.getHashAsString(newCompany.getPassword()));
            resp.sendRedirect("profile");
        } catch (AlreadyRegisteredException e) {
            resp.sendRedirect("registration-company?registerMsg=" + THIS_NAME_OR_EMAIL_IS_ALREADY_TAKEN_ERROR +
                    "&name=" + newCompany.getName() + "&phone=" + newCompany.getPhone() + "&city=" + newCompany.getCity() +
                    "&site=" + newCompany.getSite() + "&email=" + newCompany.getEmail());
        }
    }

    private Company getCompanyFromRequest(HttpServletRequest req) {
        Company company = new Company(req.getParameter("name"), req.getParameter("password"));
        company = companyService.fillCompany(company, req);
        company.setOrg(req.getParameter("org"));
        company.setCity(req.getParameter("city"));
        return company;
    }

}
