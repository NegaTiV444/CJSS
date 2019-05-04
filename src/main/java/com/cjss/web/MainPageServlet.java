package com.cjss.web;

import com.cjss.model.company.CompanyDao;
import com.cjss.model.company.MySqlCompanyDao;
import com.cjss.model.employee.EmployeeDao;
import com.cjss.model.employee.MySqlEmployeeDao;
import com.cjss.model.vacancy.MySqlVacancyDao;
import com.cjss.model.vacancy.VacancyDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageServlet extends HttpServlet {

    private final VacancyDao vacancyDao = MySqlVacancyDao.getInstance();
    private final CompanyDao companyDao = MySqlCompanyDao.getInstance();
    private final EmployeeDao employeeDao = MySqlEmployeeDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("vacancies", vacancyDao.findVacancy().size());
        req.setAttribute("companies", companyDao.findCompanies().size());
        req.setAttribute("employees", employeeDao.findEmployees().size());
        req.getRequestDispatcher("/WEB-INF/pages/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("main");
    }
}
