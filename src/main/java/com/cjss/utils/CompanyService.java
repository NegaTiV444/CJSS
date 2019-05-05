package com.cjss.utils;

import com.cjss.model.company.Company;

import javax.servlet.http.HttpServletRequest;

public class CompanyService {

    private CompanyService() {
    }

    public static CompanyService getInstance() {
        return SingletonHandler.INSTANCE;
    }

    public Company fillCompany(Company company, HttpServletRequest req) {
        company.setEmail(req.getParameter("email"));
        company.setSite(req.getParameter("site"));
        company.setPhone(req.getParameter("phone"));
        company.setFoundationDate(req.getParameter("fdate"));
        String temp = req.getParameter("sphere");
        company.setSphere(temp == null ? "" : temp);
        temp = req.getParameter("description");
        company.setDescription(temp == null ? "" : temp);
        int eCount;
        try {
            temp = req.getParameter("ecount");
            eCount = Integer.parseInt(temp);
        } catch (NumberFormatException e) {
            eCount = 0;
        }
        company.setEmployeesCount(eCount);
        temp = req.getParameter("address");
        company.setAddress(temp == null ? "" : temp);
        return company;
    }

    private static class SingletonHandler {
        static final CompanyService INSTANCE = new CompanyService();
    }
}
