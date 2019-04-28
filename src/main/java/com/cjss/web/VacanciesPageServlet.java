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

    private final VacancyDao vacancyDao = MySqlVacancyDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Vacancy> vacancies = vacancyDao.findVacancy();
        req.setAttribute("vacancies", vacancies);
        req.getRequestDispatcher("/WEB-INF/pages/vacancies.jsp").forward(req, resp);
    }
}
