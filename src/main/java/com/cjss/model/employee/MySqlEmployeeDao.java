package com.cjss.model.employee;

import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.AlreadyRegisteredException;
import com.cjss.model.exceptions.NotFoundException;
import com.cjss.utils.HashService;
import com.cjss.utils.JDBCService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class MySqlEmployeeDao implements EmployeeDao {

    private static final String TABLE = "employees";
    private HashService hashService = HashService.getInstance();
    private JDBCService jdbcService = JDBCService.getInstance();
    private Statement statement;

    private MySqlEmployeeDao() {
        try {
            statement = jdbcService.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static MySqlEmployeeDao newInstance() {
        return SingletonHandler.instance;
    }

    @Override
    public List<Employee> findEmployees() {
        List<Employee> result = new ArrayList<>();
        ResultSet resultSet;
        try {
            String query = "SELECT * FROM " + TABLE;
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(getEmployeeFromResultSet(resultSet));
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.exit(-1);
        }
        return result;
    }

    @Override
    public List<Employee> findEmployees(List<Skill> skills) { //TODO Test
        List<Employee> employees = findEmployees();
        ToIntFunction<Employee> getNumberOfTerms = employee -> (int) skills.stream()
                .filter(employee.getSkills()::contains)
                .count();

        return employees.stream()
                .filter(employee -> skills.stream().anyMatch(employee.getSkills()::contains))
                .sorted(Comparator.comparingInt(getNumberOfTerms).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Employee getEmployee(String email) throws NotFoundException {
        ResultSet resultSet;
        Employee employee = null;
        try {
            String query = "SELECT * FROM " + TABLE + " WHERE email = '" + email + "' ;";
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                employee = getEmployeeFromResultSet(resultSet);

            } else {
                throw new NotFoundException();
            }
            resultSet.close();
        } catch (SQLException e) {
            System.exit(-1);
        }
        return employee;
    }

    @Override
    public synchronized void addEmployee(Employee employee) throws AlreadyRegisteredException {
        ResultSet resultSet;
        try {
            String query = "SELECT * FROM " + TABLE + " WHERE email = '" + employee.getEmail() + "' ;";
            resultSet = statement.executeQuery(query);
            if (resultSet.first()) {
                throw new AlreadyRegisteredException();
            }
            StringBuilder skillsStr = new StringBuilder();
            for (int i = 0; i < employee.getSkills().size() - 1; i++) {
                skillsStr.append(employee.getSkills().get(i).toString());
                skillsStr.append(" ");
            }
            if (!employee.getSkills().isEmpty()) {
                skillsStr.append(employee.getSkills().get(employee.getSkills().size() - 1).toString());
            }

            query = "INSERT INTO " + TABLE + " (name, email, password, education, experience, skills, hobbies, " +
                    "other, birthDate, inSearch) VALUES ( '" + employee.getName() + "', '" +
                    employee.getEmail() + "', '" + hashService.getHashAsString(employee.getPassword()) + "', '" + employee.getEducation() +
                    "', '" + employee.getExperience() + "', '" + skillsStr.toString() + "', '" +
                    employee.getHobbies() + "', '" + employee.getOther() + "', '" + employee.getBirthDate()
                    + "', '" + employee.isInSearch() + "' );";
            statement.executeUpdate(query);
            resultSet.close();
        } catch (SQLException e) {
            System.exit(-1);
        }
    }

    @Override
    public synchronized void deleteEmployee(String email) {
        try {
            String query = "DELETE FROM " + TABLE + " WHERE email = '" + email + "' ;";
            statement.executeQuery(query);
        } catch (SQLException e) {
            System.exit(-1);
        }

    }

    private Employee getEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee(resultSet.getString("email"),
                resultSet.getString("password"));
        employee.setName(resultSet.getString("name"));
        employee.setEducation(resultSet.getString("education"));
        employee.setExperience(resultSet.getString("experience"));
        employee.setHobbies(resultSet.getString("hobbies"));
        employee.setOther(resultSet.getString("other"));
        employee.setBirthDate(resultSet.getString("birthDate"));
        employee.setInSearch(resultSet.getInt("inSearch"));
        String strSkills = resultSet.getString("skills").trim();
        String[] skills = new String[0];
        if (!strSkills.isEmpty()) {
            skills = strSkills.split(" ");
        }
        for (int i = 0; i < skills.length; i++) {
            employee.getSkills().add(Skill.valueOf(skills[i]));
        }
        return employee;
    }

    private static class SingletonHandler {
        public static MySqlEmployeeDao instance = new MySqlEmployeeDao();
    }
}
