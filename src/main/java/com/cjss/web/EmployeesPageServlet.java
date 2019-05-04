package com.cjss.web;

import com.cjss.model.employee.Employee;
import com.cjss.model.employee.EmployeeDao;
import com.cjss.model.employee.MySqlEmployeeDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmployeesPageServlet extends HttpServlet {

    private static final String EMPLOYEES_KEY = "employees";


    private final EmployeeDao employeeDao = MySqlEmployeeDao.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees = employeeDao.findEmployees();
        req.setAttribute(EMPLOYEES_KEY, employees);
        req.getRequestDispatcher("/WEB-INF/pages/employees.jsp").forward(req, resp);
    }
}
