package com.cjss.web;

import com.cjss.model.company.Company;
import com.cjss.model.company.CompanyDao;
import com.cjss.model.company.MySqlCompanyDao;
import com.cjss.utils.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanyEditProfileServlet extends HttpServlet {

    private static final String CURRENT_USER_KEY = "current user";
    private static final String COMPANY_KEY = "company";

    private final CompanyService companyService = CompanyService.getInstance();
    private final CompanyDao companyDao = MySqlCompanyDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Company company = (Company) req.getSession().getAttribute(CURRENT_USER_KEY);
            req.setAttribute(COMPANY_KEY, company);
            req.getRequestDispatcher("/WEB-INF/pages/companyEditProfile.jsp").forward(req, resp);
        } catch (ClassCastException e) {
            resp.sendRedirect("registration-company");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Company company = (Company) req.getSession().getAttribute(CURRENT_USER_KEY);
            company = companyService.fillCompany(company, req);
            req.setAttribute(COMPANY_KEY, company);
            companyDao.updateCompany(company);
            req.getRequestDispatcher("/WEB-INF/pages/companyProfile.jsp").forward(req, resp);
        } catch (ClassCastException e) {
            resp.sendRedirect("registration-company");
        }
    }
}
