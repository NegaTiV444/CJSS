package com.cjss.model.vacancy;

import com.cjss.model.company.Company;
import com.cjss.model.company.MySqlCompanyDao;
import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.NotFoundException;
import com.cjss.utils.JDBCService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public static MySqlVacancyDao newInstance() {
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
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.exit(-1);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Vacancy> findVacancy(String query) {
        return null;
    }

    @Override
    public List<Vacancy> findVacancy(Company company) {
        return null;
    }

    @Override
    public List<Vacancy> findVacancy(List<Skill> skills) {
        return null;
    }

    @Override
    public void addVacancy(Vacancy vacancy) {
        try {
            String query;
            StringBuilder skillsStr = new StringBuilder();
            for (int i = 0; i < vacancy.getRequiredSkills().size() - 1; i++) {
                skillsStr.append(vacancy.getRequiredSkills().get(i).toString());
                skillsStr.append(" ");
            }
            if (!vacancy.getRequiredSkills().isEmpty()) {
                skillsStr.append(vacancy.getRequiredSkills().get(vacancy.getRequiredSkills().size() - 1).toString());
            }
            query = "INSERT INTO " + TABLE + " (position, companyName, location, description, skills) VALUES " +
                    "( '" + vacancy.getPosition() + "', '" +
                    vacancy.getCompany().getName() + "', '" + vacancy.getLocation() + "', '" + vacancy.getDescription() +
                    "', '" + skillsStr.toString() + "' );";
            PreparedStatement statement = jdbcService.getConnection().prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate(query);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating vacancy failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    vacancy.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

            statement.close();
        } catch (SQLException e) {
            System.exit(-1);
        }
    }

    @Override
    public void deleteVacancy(Vacancy vacancy) {

    }

    private Vacancy getVacancyFromResultSet(ResultSet resultSet) throws SQLException, NotFoundException {
        Vacancy vacancy = new Vacancy();
        vacancy.setCompany(companyDao.getCompany(resultSet.getString("companyName")));
        vacancy.setDescription(resultSet.getString("description"));
        vacancy.setLocation(resultSet.getString("location"));
        vacancy.setPosition(resultSet.getString("position"));
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
