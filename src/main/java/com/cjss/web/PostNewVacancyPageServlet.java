package com.cjss.web;

import com.cjss.model.company.Company;
import com.cjss.model.enums.Skill;
import com.cjss.model.vacancy.MySqlVacancyDao;
import com.cjss.model.vacancy.Vacancy;
import com.cjss.model.vacancy.VacancyDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostNewVacancyPageServlet extends HttpServlet {

    private static final String CURRENT_USER_KEY = "current user";

    private final VacancyDao vacancyDao = MySqlVacancyDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Company company = null;
        try {
            company = (Company)req.getSession().getAttribute(CURRENT_USER_KEY);
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
        Company company = (Company)req.getSession().getAttribute(CURRENT_USER_KEY);
        vacancy.setCompanyName(company.getName());
        vacancy.setPosition(req.getParameter("position"));
        vacancy.setDescription(req.getParameter("description"));
        vacancy.setLocation(req.getParameter("location"));
        vacancy.setConditions(req.getParameter("conditions"));
        vacancy.getRequiredSkills().addAll(parseSkills(req));
        vacancyDao.addVacancy(vacancy);
        resp.sendRedirect("vacancies");
    }

    private List<Skill> parseSkills(HttpServletRequest req) {
        List<Skill> skills = new ArrayList<>();
        String temp;
        for (Skill skill : Skill.values()) {
            temp = req.getParameter(skill.toString());
            if (temp != null)
                skills.add(skill);
        }
        return skills;
    }
}
