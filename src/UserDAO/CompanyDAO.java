package UserDAO;

import UserBean.*;
import UserDB.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    private Connection connection , connection2;

    public CompanyDAO() {
    }

    public void addCompany(CompanyBean company)  {

        connection= CompanyDB.getIndexConnection("jdbc:mysql://localhost:3306/companiesDB");
        connection2 = CompanyDB.getConnection("jdbc:mysql://localhost:3306");
//TO THINK ABOUT
//        Statement statement = null;
//        try {

//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO companiesTable(name) VALUES (?)");

            // Parameters start with 1
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.executeUpdate();

            Statement statement;
            int newCompanyID = 0;
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(companyID) FROM companiesTable");

            while (rs.next()) {
                newCompanyID = rs.getInt("id");
            }

            Statement stm = connection2.createStatement();
            stm.executeUpdate("CREATE DATABASE `company" + newCompanyID +"`");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllCompanies()  {
        connection= CompanyDB.getIndexConnection("jdbc:mysql://localhost:3306/companiesDB");
        List<String> companies = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM companiesTable");
            while (rs.next()) {
                companies.add(rs.getString("companyName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

}