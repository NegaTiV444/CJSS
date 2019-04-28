package com.cjss.web;

import com.cjss.model.vacancy.MySqlVacancyDao;
import com.cjss.model.vacancy.Vacancy;
import com.cjss.model.vacancy.VacancyDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class VacanciesPageServlet extends HttpServlet {

    private static final String VACANCIES_KEY = "vacancies";

    private final VacancyDao vacancyDao = MySqlVacancyDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        List<Vacancy> vacancies = vacancyDao.findVacancy(query);
        req.setAttribute(VACANCIES_KEY, vacancies);
        req.getRequestDispatcher("/WEB-INF/pages/vacancies.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
