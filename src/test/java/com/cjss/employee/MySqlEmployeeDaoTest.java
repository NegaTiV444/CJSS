package com.cjss.employee;

import com.cjss.model.employee.Employee;
import com.cjss.model.employee.EmployeeDao;
import com.cjss.model.employee.MySqlEmployeeDao;
import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.AlreadyRegisteredException;
import com.cjss.model.exceptions.NotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MySqlEmployeeDaoTest {

    private static final EmployeeDao employeeDao = MySqlEmployeeDao.getInstance();
    private static final Employee testEmployee = new Employee("test@email2.com", "pass2");

    @BeforeClass
    public static void init() {
        testEmployee.setName("Test Employee");
        testEmployee.setHobbies("Playing Dota 2 =(");
        testEmployee.setExperience("McDonals");
        testEmployee.setOther("Can reinstall Windows");
        testEmployee.setEducation("BSUIR");
        testEmployee.setBirthDate("01.01.2000");
        testEmployee.getSkills().add(Skill.ANDROID);
        testEmployee.getSkills().add(Skill.JAVA);
        testEmployee.getSkills().add(Skill.SWIFT);
    }

    @AfterClass
    public static void after() {
        try {
            employeeDao.deleteEmployee(testEmployee.getEmail());
        } catch (NotFoundException e) {
        }
    }

    @After
    public void clean() {
        try {
            employeeDao.deleteEmployee(testEmployee.getEmail());
        } catch (NotFoundException e) {
        }
    }

    @Test
    public void findEmployeesTest() throws AlreadyRegisteredException, NotFoundException {
        employeeDao.addEmployee(testEmployee);
        List<Employee> employees = employeeDao.findEmployees();
        assertFalse(employees.isEmpty());
    }

    @Test
    public void findEmployeesBySkillsTest() throws AlreadyRegisteredException, NotFoundException {
        employeeDao.addEmployee(testEmployee);
        List<Skill> skills = new ArrayList<>();
        skills.add(Skill.ANDROID);
        assertTrue(employeeDao.findEmployees(skills).contains(testEmployee));
        skills.clear();
        skills.add(Skill.DELPHY);
        assertFalse(employeeDao.findEmployees(skills).contains(testEmployee));
    }

    @Test
    public void addAndGetEmployeeTest() throws AlreadyRegisteredException, NotFoundException {
        employeeDao.addEmployee(testEmployee);
        assertEquals(testEmployee, employeeDao.getEmployee(testEmployee.getId()));
    }

    @Test(expected = NotFoundException.class)
    public void getEmployeeWithWrongEmailTest() throws AlreadyRegisteredException, NotFoundException {
        employeeDao.addEmployee(testEmployee);
        employeeDao.getEmployee("wrong@email.com");
    }

    @Test(expected = NotFoundException.class)
    public void getEmployeeWithWrongIdTest() throws AlreadyRegisteredException, NotFoundException {
        employeeDao.addEmployee(testEmployee);
        employeeDao.getEmployee(4204967295L);
    }

    @Test(expected = AlreadyRegisteredException.class)
    public void addOneEmployeeTwiceTest() throws AlreadyRegisteredException {
        employeeDao.addEmployee(testEmployee);
        employeeDao.addEmployee(testEmployee);
    }

    @Test(expected = NotFoundException.class)
    public void deleteEmployeeTest() throws AlreadyRegisteredException, NotFoundException {
        employeeDao.addEmployee(testEmployee);
        employeeDao.deleteEmployee(testEmployee.getEmail());
        employeeDao.getEmployee(testEmployee.getEmail());
    }

    @Test(expected = NotFoundException.class)
    public void deleteEmployeeWithWrongEmailTest() throws AlreadyRegisteredException, NotFoundException {
        employeeDao.addEmployee(testEmployee);
        employeeDao.deleteEmployee("wrong@email.com");
    }

    @Test(expected = NotFoundException.class)
    public void deleteEmployeeWithWrongIdTest() throws AlreadyRegisteredException, NotFoundException {
        employeeDao.addEmployee(testEmployee);
        employeeDao.deleteEmployee(4204967295L);
    }

    @Test
    public void UpdateEmployeeTest() throws NotFoundException, AlreadyRegisteredException {
        List<Skill> skillsBefore = new ArrayList<>(testEmployee.getSkills());
        testEmployee.getSkills().add(Skill.DOTNET);
        employeeDao.addEmployee(testEmployee);
        employeeDao.updateEmployee(testEmployee);
        skillsBefore.add(Skill.DOTNET);
        assertEquals(skillsBefore, employeeDao.getEmployee(testEmployee.getEmail()).getSkills());
        testEmployee.getSkills().remove(Skill.DOTNET);
    }

}
