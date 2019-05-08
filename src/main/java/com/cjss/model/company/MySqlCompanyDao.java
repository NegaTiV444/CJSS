package com.cjss.model.company;

import com.cjss.model.exceptions.AlreadyRegisteredException;
import com.cjss.model.exceptions.NotFoundException;
import com.cjss.utils.HashService;
import com.cjss.utils.JDBCService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlCompanyDao implements CompanyDao {

    private static final String TABLE = "companies";
    private HashService hashService = HashService.getInstance();
    private JDBCService jdbcService = JDBCService.getInstance();
    private Statement statement;

    private MySqlCompanyDao() {
        try {
            statement = jdbcService.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static MySqlCompanyDao getInstance() {
        return SingletonHandler.INSTANCE;
    }

    @Override
    public List<Company> findCompanies() {
        List<Company> result = new ArrayList<>();
        ResultSet resultSet;
        try {
            String query = "SELECT * FROM " + TABLE;
            PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(getCompanyFromResultSet(resultSet));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.exit(-1);
        }
        return result;
    }


    @Override
    public Company getCompany(String query) throws NotFoundException {
        ResultSet resultSet;
        Company company = null;
        try {
            String sqlQuery = "SELECT * FROM " + TABLE + " WHERE name = '" + query + "';";
            PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                sqlQuery = "SELECT * FROM " + TABLE + " WHERE email = '" + query + "';";
                preparedStatement = jdbcService.getConnection().prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
                resultSet = preparedStatement.executeQuery();
            }
            resultSet.beforeFirst();
            if (resultSet.next()) {
                company = getCompanyFromResultSet(resultSet);
            } else {
                throw new NotFoundException();
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.exit(-1);
        }
        return company;
    }

    @Override
    public synchronized void addCompany(Company company) throws AlreadyRegisteredException {
        ResultSet resultSet;
        try {
            String query = "SELECT * FROM " + TABLE + " WHERE name = '" + company.getName() + "' ;";
            PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                throw new AlreadyRegisteredException();
            }
            query = "INSERT INTO " + TABLE + " (org, name, password, city, email, phone, site, address, fdate, " +
                    "sphere, description, ecount) VALUES ( '"
                    + company.getOrg() + "', '" + company.getName() + "', '"
                    + hashService.getHashAsString(company.getPassword()) + "', '"
                    + company.getCity() + "', '" + company.getEmail() + "', '"
                    + company.getPhone() + "', '" + company.getSite() + "', '" + company.getAddress() + "', '"
                    + company.getFoundationDate() + "', '" + company.getSphere() + "', '"
                    + company.getDescription() + "', '" + company.getEmployeesCount() + "' );";
            preparedStatement.executeUpdate(query);
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.exit(-1);
        }
    }

    @Override
    public synchronized void deleteCompany(String name) throws NotFoundException {
        try {
            getCompany(name);
            String query = "DELETE FROM " + TABLE + " WHERE name = '" + name + "' ;";
            PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.exit(-1);
        }
    }

    @Override
    public void updateCompany(Company updatedCompany) throws NotFoundException {
        ResultSet resultSet;
        try {
            Company company;
            StringBuilder query = new StringBuilder("SELECT * FROM " + TABLE + " WHERE name = '" + updatedCompany.getName() + "' ;");
            PreparedStatement preparedStatement = jdbcService.getConnection().prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                company = getCompanyFromResultSet(resultSet);
                query = new StringBuilder("UPDATE " + TABLE);
                query.append(" SET email = '" + updatedCompany.getEmail() + "', ");
                query.append(" site = '" + updatedCompany.getSite() + "', ");
                query.append(" fdate = '" + updatedCompany.getFoundationDate() + "', ");
                query.append(" address = '" + updatedCompany.getAddress() + "', ");
                query.append(" phone = '" + updatedCompany.getPhone() + "', ");
                query.append(" sphere = '" + updatedCompany.getSphere() + "', ");
                query.append(" ecount = '" + updatedCompany.getEmployeesCount() + "', ");
                query.append(" description = '" + updatedCompany.getDescription() + "' ");
                query.append(" WHERE name = '" + company.getName() + "';");
                preparedStatement.executeUpdate(query.toString());
            } else {
                throw new NotFoundException();
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.exit(-1);
        }
    }

    private Company getCompanyFromResultSet(ResultSet resultSet) throws SQLException {
        Company company = new Company(resultSet.getString("name"), resultSet.getString("password"));
        company.setCity(resultSet.getString("city"));
        company.setOrg(resultSet.getString("org"));
        company.setEmail(resultSet.getString("email"));
        company.setPhone(resultSet.getString("phone"));
        company.setSite(resultSet.getString("site"));
        company.setAddress(resultSet.getString("address"));
        company.setDescription(resultSet.getString("description"));
        company.setEmployeesCount(resultSet.getInt("ecount"));
        company.setFoundationDate(resultSet.getString("fdate"));
        company.setSphere(resultSet.getString("sphere"));
        return company;
    }

    private static class SingletonHandler {
        final static MySqlCompanyDao INSTANCE = new MySqlCompanyDao();
    }
}
