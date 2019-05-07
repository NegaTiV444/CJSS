package com.cjss.company;

import com.cjss.model.company.Company;
import com.cjss.model.company.CompanyDao;
import com.cjss.model.company.MySqlCompanyDao;
import com.cjss.model.exceptions.AlreadyRegisteredException;
import com.cjss.model.exceptions.NotFoundException;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MySqlCompanyDaoTest {

    private static final CompanyDao companyDao = MySqlCompanyDao.getInstance();

    private static final Company testCompany = new Company("Test Company 1", "pass1");

    @BeforeClass
    public static void init() {
        testCompany.setDescription("Company for MySqlCompanyDaoTest");
        testCompany.setAddress("Main street 1");
        testCompany.setEmployeesCount(13);
        testCompany.setSphere("Mobile development");
        testCompany.setFoundationDate("01.01.1970");
        testCompany.setCity("Minsk");
        testCompany.setOrg("LTD");
        testCompany.setEmail("test@email.com");
        testCompany.setPhone("911");
        testCompany.setSite("test.com");
    }

    @After
    public void clean() {
        try {
            companyDao.deleteCompany(testCompany.getName());
        } catch (NotFoundException e) {
        }
    }

    @Test
    public void addCompanyTest() throws AlreadyRegisteredException, NotFoundException {
        companyDao.addCompany(testCompany);
        assertEquals(testCompany, companyDao.getCompany(testCompany.getName()));
    }

    @Test(expected = AlreadyRegisteredException.class)
    public void addOneCompanyTwiceTest() throws AlreadyRegisteredException {
        companyDao.addCompany(testCompany);
        companyDao.addCompany(testCompany);
    }

    @Test
    public void findCompaniesTest() throws AlreadyRegisteredException {
        companyDao.addCompany(testCompany);
        List<Company> companies = companyDao.findCompanies();
        assertTrue(companies.contains(testCompany));
    }

    @Test
    public void getCompanyTest() throws AlreadyRegisteredException, NotFoundException {
        companyDao.addCompany(testCompany);
        assertEquals(testCompany, companyDao.getCompany(testCompany.getName()));
        assertEquals(testCompany, companyDao.getCompany(testCompany.getEmail()));
    }

    @Test(expected = NotFoundException.class)
    public void getIncorrectCompanyTest() throws AlreadyRegisteredException, NotFoundException {
        companyDao.addCompany(testCompany);
        assertEquals(testCompany, companyDao.getCompany("Test Company 2"));
    }

    @Test
    public void deleteCompanyTest() throws AlreadyRegisteredException, NotFoundException {
        companyDao.addCompany(testCompany);
        companyDao.deleteCompany(testCompany.getName());
        companyDao.addCompany(testCompany);
    }

    @Test(expected = NotFoundException.class)
    public void deleteCompanyWithWrongNameTest() throws AlreadyRegisteredException, NotFoundException {
        companyDao.addCompany(testCompany);
        companyDao.deleteCompany("Wrong name");
    }

    @Test
    public void updateCompanyTest() throws AlreadyRegisteredException, NotFoundException {
        companyDao.addCompany(testCompany);
        int employeesCountBefore = testCompany.getEmployeesCount();
        testCompany.setEmployeesCount(employeesCountBefore + 1);
        companyDao.updateCompany(testCompany);
        assertEquals(employeesCountBefore + 1, companyDao.getCompany(testCompany.getName()).getEmployeesCount());
        testCompany.setEmployeesCount(employeesCountBefore);
        companyDao.updateCompany(testCompany);
    }
}
