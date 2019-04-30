package com.cjss.model.employee;

import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.AlreadyRegisteredException;
import com.cjss.model.exceptions.NotFoundException;

import java.util.List;

public interface EmployeeDao {

    List<Employee> findEmployees();
    List<Employee> findEmployees(List<Skill> skills);
    Employee getEmployee (String email) throws NotFoundException;
    void addEmployee(Employee employee) throws AlreadyRegisteredException;
    void deleteEmployee(String email) throws NotFoundException;
    void updateEmployee(Employee updatedEmployee) throws NotFoundException;
}
