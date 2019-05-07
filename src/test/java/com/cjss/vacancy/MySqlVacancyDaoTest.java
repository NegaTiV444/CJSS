package com.cjss.vacancy;

import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.NotFoundException;
import com.cjss.model.vacancy.MySqlVacancyDao;
import com.cjss.model.vacancy.Vacancy;
import com.cjss.model.vacancy.VacancyDao;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MySqlVacancyDaoTest {

    private static final VacancyDao vacancyDao = MySqlVacancyDao.getInstance();
    private static final Vacancy testVacancy = new Vacancy();

    @BeforeClass
    public static void init() {
        testVacancy.setLocation("Planet Earth");
        testVacancy.setCompanyName("New World Order Company");
        testVacancy.setPosition("Android senior-developer");
        testVacancy.setDescription("Develop our new Android-application - AI voice-assistant Lucifer");
        testVacancy.setConditions("Great office in the city centre. Tasty coffee. Work 16 hours a day.");
        testVacancy.getRequiredSkills().add(Skill.JAVA);
        testVacancy.getRequiredSkills().add(Skill.C);
        testVacancy.getRequiredSkills().add(Skill.CPP);
    }

    @After
    public void clean() {
        try {
            vacancyDao.deleteVacancy(testVacancy.getId());
        } catch (NotFoundException e) {
        }
    }

    @Test
    public void addAndGetVacancyTest() throws NotFoundException {
        vacancyDao.addVacancy(testVacancy);
        assertEquals(testVacancy, vacancyDao.getVacancy(testVacancy.getId()));
    }

    @Test(expected = NotFoundException.class)
    public void deleteVacancyTest() throws NotFoundException {
        vacancyDao.addVacancy(testVacancy);
        vacancyDao.deleteVacancy(testVacancy.getId());
        vacancyDao.getVacancy(testVacancy.getId());
    }

    @Test
    public void findVacancyBySkillsTest() throws NotFoundException {
        List<Skill> skills = new ArrayList<>(testVacancy.getRequiredSkills());
        vacancyDao.addVacancy(testVacancy);
        assertTrue(vacancyDao.findVacancy(skills).contains(testVacancy));
    }

    @Test
    public void findVacancyBySkillsWithWrongDataTest(){
        List<Skill> skills = new ArrayList<>(testVacancy.getRequiredSkills());
        vacancyDao.addVacancy(testVacancy);
        skills.clear();
        assertTrue(vacancyDao.findVacancy(skills).isEmpty());
    }

    @Test
    public void findByQueryVacancyTest() {
        vacancyDao.addVacancy(testVacancy);
        assertTrue(vacancyDao.findVacancy("New World Order").contains(testVacancy));
        assertTrue(vacancyDao.findVacancy("Android-application").contains(testVacancy));
        assertTrue(vacancyDao.findVacancy("Earth").contains(testVacancy));
        assertTrue(vacancyDao.findVacancy("senior").contains(testVacancy));
    }

    @Test
    public void findVacancyByCompanyTest() {
        vacancyDao.addVacancy(testVacancy);
        assertTrue(vacancyDao.findVacancy(testVacancy.getCompanyName()).contains(testVacancy));
    }
}
