package com.cjss.web;

import com.cjss.model.employee.Employee;
import com.cjss.model.employee.EmployeeDao;
import com.cjss.model.employee.MySqlEmployeeDao;
import com.cjss.utils.EmployeeService;
import com.cjss.utils.HashService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EmployeeRegistrationPageServlet extends HttpServlet {

    private static final String THIS_EMAIL_IS_ALREADY_TAKEN_ERROR = "email.is.taken.error";

    private final HashService hashService = HashService.getInstance();
    private final EmployeeService employeeService = EmployeeService.getInstance();
    private final EmployeeDao employeeDao = MySqlEmployeeDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/employeeRegistration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee newEmployee = getEmployeeFromRequest(req);
        if (employeeService.checkEmployeeExists(newEmployee)) {
            resp.sendRedirect("registration-employee?registerMsg=" + THIS_EMAIL_IS_ALREADY_TAKEN_ERROR);
        } else {
            employeeDao.addEmployee(newEmployee);
            HttpSession session = req.getSession();
            session.setAttribute("email", newEmployee.getEmail());
            session.setAttribute("password", hashService.getHashAsString(newEmployee.getPassword()));
            resp.sendRedirect("profile");
        }
    }

    private Employee getEmployeeFromRequest(HttpServletRequest req) {
        Employee employee = new Employee(req.getParameter("email"), req.getParameter("password"));
        employee.setName(req.getParameter("first_name") + " " + req.getParameter("last_name"));
        employee = employeeService.fillEmployee(employee, req);
        return employee;
    }
}
