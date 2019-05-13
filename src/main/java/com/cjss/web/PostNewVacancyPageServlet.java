package com.cjss.web;

import com.cjss.model.company.Company;
import com.cjss.model.vacancy.MySqlVacancyDao;
import com.cjss.model.vacancy.Vacancy;
import com.cjss.model.vacancy.VacancyDao;
import com.cjss.utils.SkillsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostNewVacancyPageServlet extends HttpServlet {

    private static final String CURRENT_USER_KEY = "current user";

    private final SkillsService skillsService = SkillsService.getInstance();
    private final VacancyDao vacancyDao = MySqlVacancyDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Company company = null;
        try {
            company = (Company) req.getSession().getAttribute(CURRENT_USER_KEY);
            if (company == null) {
                resp.sendRedirect("registration-company");
            } else {
                req.getRequestDispatcher("/WEB-INF/pages/postVacancy.jsp").forward(req, resp);
            }
        } catch (ClassCastException e) {
            resp.sendRedirect("registration-company");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Vacancy vacancy = new Vacancy();
        Company company = (Company) req.getSession().getAttribute(CURRENT_USER_KEY);
        vacancy.setCompanyName(company.getName());
        vacancy.setPosition(req.getParameter("position"));
        vacancy.setDescription(req.getParameter("description"));
        vacancy.setLocation(req.getParameter("location"));
        vacancy.setConditions(req.getParameter("conditions"));
        vacancy.getRequiredSkills().addAll(skillsService.parseSkills(req.getParameterMap()));
        vacancyDao.addVacancy(vacancy);
        resp.sendRedirect("companies/vacanciesManagement");
    }


}
