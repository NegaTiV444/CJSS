package com.cjss.model.employee;

import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.UserAlreadyRegisteredException;
import com.cjss.model.exceptions.UserNotFoundException;

import java.util.List;

public interface EmployeeDao {

    List<Employee> findUsers();
    List<Employee> findUsers(String query);
    List<Employee> findUsers(List<Skill> skills);
    Employee getEmployee (String email) throws UserNotFoundException;
    void addEmployee(Employee employee) throws UserAlreadyRegisteredException;
    void deleteEmployee(String userName);
}
