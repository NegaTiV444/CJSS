package com.cjss.web;

import com.cjss.model.company.Company;
import com.cjss.model.vacancy.MySqlVacancyDao;
import com.cjss.model.vacancy.Vacancy;
import com.cjss.model.vacancy.VacancyDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PostNewVacancyPageServlet extends HttpServlet {

    private static final String COMPANY_KEY = "company";

    private final VacancyDao vacancyDao = MySqlVacancyDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Company company = (Company)req.getSession().getAttribute(COMPANY_KEY);
        if (company == null) {
            resp.sendRedirect("registration-company");
        } else {
            List<Vacancy> vacancies = vacancyDao.findVacancy(company.getName()); //TODO do
        }
    }
}
