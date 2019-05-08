package com.cjss.web;

import com.cjss.model.vacancy.MySqlVacancyDao;
import com.cjss.model.vacancy.Vacancy;
import com.cjss.model.vacancy.VacancyDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VacancyPageServlet extends HttpServlet {

    private static final String VACANCY_KEY = "vacancy";

    private final VacancyDao vacancyDao = MySqlVacancyDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Vacancy vacancy = vacancyDao.getVacancy(Integer.parseInt(req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/') + 1)));
        req.setAttribute(VACANCY_KEY, vacancy);
        req.getRequestDispatcher("/WEB-INF/pages/vacancy.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Integer.parseInt(req.getRequestURI().substring(req.getRequestURI().lastIndexOf('/') + 1));
        vacancyDao.deleteVacancy(id);
        resp.sendRedirect("../companies/vacanciesManagement");
    }
}
