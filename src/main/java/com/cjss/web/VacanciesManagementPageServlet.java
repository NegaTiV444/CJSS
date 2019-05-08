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

public class VacanciesManagementPageServlet extends HttpServlet {

    private static final String CURRENT_USER_KEY = "current user";
    private static final String VACANCIES_KEY = "vacancies";

    private final VacancyDao vacancyDao = MySqlVacancyDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Company company = (Company) req.getSession().getAttribute(CURRENT_USER_KEY);
            List<Vacancy> vacancies = vacancyDao.findVacancyByCompany(company.getName());
            req.setAttribute(VACANCIES_KEY, vacancies);
            req.getRequestDispatcher("/WEB-INF/pages/vacanciesManagement.jsp").forward(req, resp);
        } catch (ClassCastException e) {
            resp.sendRedirect("registration-company");
        }
    }
}
