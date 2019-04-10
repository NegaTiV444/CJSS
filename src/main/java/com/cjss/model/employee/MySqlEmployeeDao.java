package com.cjss.model.employee;

import com.cjss.model.enums.Skill;
import com.cjss.model.exceptions.UserAlreadyRegisteredException;
import com.cjss.model.exceptions.UserNotFoundException;
import com.cjss.utils.HashService;
import com.cjss.utils.JDBCService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;

public class MySqlEmployeeDao implements EmployeeDao {

    private HashService hashService = HashService.getInstance();

    private static final String TABLE = "employees";

    private JDBCService jdbcService;
    private Statement statement;
    private ResultSet resultSet;

    private MySqlEmployeeDao() {
        jdbcService = JDBCService.newInstance();
    }

    public static MySqlEmployeeDao newInstance() {
        return SingletonHandler.instance;
    }

    @Override
    public List<Employee> findUsers() {
        return null;
    }

    @Override
    public List<Employee> findUsers(String query) {
        if ((query == null) || (query.trim().isEmpty()))
            return findUsers();
        String[] terms = query.toLowerCase().split(" ");
        ToIntFunction<Employee> getNumberOfTerms = product -> (int) Arrays.stream(terms)
                .filter((product.getName()).toLowerCase()::contains)
                .count();

//        return products.stream()
//                .filter(isProductCorrect)
//                .filter(product -> Arrays.stream(terms).anyMatch(product.getDescription().toLowerCase()::contains))
//                .sorted(Comparator.comparingInt(getNumberOfTerms).reversed())
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public synchronized List<Employee> findUsers(List<Skill> skills) {
        return null;
    }

    @Override
    public Employee getEmployee(String email) throws UserNotFoundException {
        Employee employee = null;
        try {
            statement = jdbcService.getConnection().createStatement();
            String query = "SELECT * FROM " + TABLE + " WHERE email = '" + email + "' ;";
            resultSet = statement.executeQuery(query);
            resultSet.first();
            employee = getEmployeeFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new UserNotFoundException();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employee;
    }

    @Override
    public synchronized void addEmployee(Employee employee) throws UserAlreadyRegisteredException {
        try {
            Statement statement = jdbcService.getConnection().createStatement();
            String query = "SELECT * FROM " + TABLE + " WHERE email = '" + employee.getEmail() + "' ;";
            resultSet = statement.executeQuery(query);
            if (resultSet.first()){
                throw new UserAlreadyRegisteredException();
            }
            StringBuilder skillsStr = new StringBuilder();
            for (int i = 0; i < employee.getSkills().size() - 1; i++){
                skillsStr.append(employee.getSkills().get(i).toString());
                skillsStr.append(" ");
            }
            if (!employee.getSkills().isEmpty()){
                skillsStr.append(employee.getSkills().get(employee.getSkills().size() - 1).toString());
            }

            query = "INSERT INTO " + TABLE + " (name, email, password, education, experience, skills, hobbies, " +
                    "other, birthDate, inSearch) VALUES ( '" + employee.getName() + "', '" +
                    employee.getEmail() + "', '" + hashService.getHashAsString(employee.getPassword()) + "', '" + employee.getEducation() +
                     "', '" + employee.getExperience() + "', '" + skillsStr.toString() + "', '" +
                    employee.getHobbies() + "', '" + employee.getOther() + "', '" + employee.getBirthDate()
                    + "', '" + employee.isInSearch() + "' );";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public synchronized void deleteEmployee(String userName) {

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
        if (!strSkills.isEmpty()){
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
