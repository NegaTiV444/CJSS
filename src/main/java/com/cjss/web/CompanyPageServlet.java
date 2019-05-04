package com.cjss.web;

import com.cjss.model.company.Company;
import com.cjss.model.company.CompanyDao;
import com.cjss.model.company.MySqlCompanyDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanyPageServlet extends HttpServlet {

    private static final String COMPANY_KEY = "company";

    private CompanyDao companyDao = MySqlCompanyDao.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Company company = companyDao.getCompany(req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/') + 1));
        req.setAttribute(COMPANY_KEY, company);
        req.getRequestDispatcher("/WEB-INF/pages/company.jsp").forward(req, resp);
    }
}
