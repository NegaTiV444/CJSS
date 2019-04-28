package com.cjss.model.vacancy;

import com.cjss.model.company.Company;
import com.cjss.model.company.CompanyDao;
import com.cjss.model.company.MySqlCompanyDao;
import com.cjss.model.employee.Employee;
import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.NotFoundException;
import com.cjss.utils.JDBCService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class MySqlVacancyDao implements VacancyDao {

    private static final String TABLE = "vacancies";
    private JDBCService jdbcService = JDBCService.getInstance();
    private MySqlCompanyDao companyDao = MySqlCompanyDao.getInstance();
    private Statement statement;

    private MySqlVacancyDao() {
        try {
            statement = jdbcService.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static MySqlVacancyDao getInstance() {
        return SingletonHandler.INSTANCE;
    }

    @Override
    public Vacancy getVacancy(int id) throws NotFoundException {
        ResultSet resultSet;
        Vacancy vacancy = null;
        try {
            String query = "SELECT * FROM " + TABLE + " WHERE id = '" + id + "' ;";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                vacancy = getVacancyFromResultSet(resultSet);
            } else {
                throw new NotFoundException();
            }
            resultSet.close();
        } catch (SQLException e) {
            System.exit(-1);
        }
        return vacancy;
    }

    @Override
    public List<Vacancy> findVacancy() {
        List<Vacancy> result = new ArrayList<>();
        ResultSet resultSet;
        try {
            String query = "SELECT * FROM " + TABLE;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(getVacancyFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.exit(-1);
        }
        return result;
    }

    @Override
    public List<Vacancy> findVacancy(String query) {
        List<Vacancy> vacancies = findVacancy();
        if (query == null) {
            return vacancies;
        }
        List<Vacancy> result;
        String[] terms = query.toLowerCase().trim().split(" +");
        result = vacancies.stream()
                .filter(vacancy -> Arrays.stream(terms)
                        .anyMatch(vacancy.getCompanyName().toLowerCase()::contains))
                .collect(Collectors.toList());
        Set<Vacancy> vacancySet = new HashSet<>(result);
        result.addAll(vacancies.stream()
                .filter(vacancy -> !vacancySet.contains(vacancy))
                .filter(vacancy -> Arrays.stream(terms)
                        .anyMatch(vacancy.getLocation().toLowerCase()::contains))
                .collect(Collectors.toList()));
        vacancySet.addAll(result);
        result.addAll(vacancies.stream()
                .filter(vacancy -> !vacancySet.contains(vacancy))
                .filter(vacancy -> Arrays.stream(terms)
                        .anyMatch(vacancy.getPosition().toLowerCase()::contains))
                .collect(Collectors.toList()));
        vacancySet.addAll(result);
        result.addAll(vacancies.stream()
                .filter(vacancy -> !vacancySet.contains(vacancy))
                .filter(vacancy -> Arrays.stream(terms)
                        .anyMatch(vacancy.getDescription().toLowerCase()::contains))
                .collect(Collectors.toList()));
        return result;
    }

    @Override
    public List<Vacancy> findVacancyByCompany(String companyName) {
        List<Vacancy> result = new ArrayList<>();
        ResultSet resultSet;
        try {
            String query = "SELECT * FROM " + TABLE + " WHERE companyName = '" + companyName + "' ;";
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(getVacancyFromResultSet(resultSet));
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.exit(-1);
        }
        return result;
    }

    @Override
    public List<Vacancy> findVacancy(List<Skill> skills) {
        List<Vacancy> vacancies = findVacancy();
        ToIntFunction<Vacancy> getNumberOfTerms = vacancy -> (int) skills.stream()
                .filter(vacancy.getRequiredSkills()::contains)
                .count();

        return vacancies.stream()
                .filter(vacancy -> skills.stream().anyMatch(vacancy.getRequiredSkills()::contains))
                .sorted(Comparator.comparingInt(getNumberOfTerms).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void addVacancy(Vacancy vacancy) {
        try {
            String query;
            StringBuilder skillsStr = new StringBuilder();
            if (!vacancy.getRequiredSkills().isEmpty()){
                skillsStr.append(vacancy.getRequiredSkills().get(0));
                for (int i = 0; i < vacancy.getRequiredSkills().size() - 1; i++) {
                    skillsStr.append(vacancy.getRequiredSkills().get(i).toString());
                    skillsStr.append(" ");
                }
            }
            if (vacancy.getRequiredSkills().size() > 1) {
                skillsStr.append(vacancy.getRequiredSkills().get(vacancy.getRequiredSkills().size() - 1).toString());
            }
            query = "INSERT INTO " + TABLE + " (position, companyName, location, description, skills, conditions) VALUES " +
                    "( '" + vacancy.getPosition() + "', '" +
                    vacancy.getCompanyName() + "', '" + vacancy.getLocation() + "', '" + vacancy.getDescription() +
                    "', '" + skillsStr.toString() + "', '" + vacancy.getConditions() + "' );";
            PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating vacancy failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    vacancy.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            preparedStatement.close();
        } catch (SQLException e) {
            System.exit(-1);
        }
    }

    @Override
    public void deleteVacancy(Vacancy vacancy) {
        try {
            String query = "DELETE FROM " + TABLE + " WHERE id = '" + vacancy.getId() + "' ;";
            statement.executeQuery(query);
        } catch (SQLException e) {
            System.exit(-1);
        }
    }

    private Vacancy getVacancyFromResultSet(ResultSet resultSet) throws SQLException {
        Vacancy vacancy = new Vacancy();
        vacancy.setCompanyName(resultSet.getString("companyName"));
        vacancy.setDescription(resultSet.getString("description"));
        vacancy.setLocation(resultSet.getString("location"));
        vacancy.setPosition(resultSet.getString("position"));
        vacancy.setConditions(resultSet.getString("conditions"));
        String strSkills = resultSet.getString("skills").trim();
        String[] skills = new String[0];
        if (!strSkills.isEmpty()) {
            skills = strSkills.split(" ");
        }
        for (String skill : skills) {
            vacancy.getRequiredSkills().add(Skill.valueOf(skill));
        }
        return vacancy;
    }

    private static class SingletonHandler {
        static final MySqlVacancyDao INSTANCE = new MySqlVacancyDao();
    }
}
