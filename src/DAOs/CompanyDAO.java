package DAOs;

import Beans.*;
import DB.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    private Connection connection;

    public CompanyDAO() {
        connection= DB.getConnection();
    }

    public void addCompany(CompanyBean company)  {
        try {
            ResultSet rs ;
            Statement statement = connection.createStatement();

            String newCompanyName = company.getCompanyName();

            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + newCompanyName);
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+ newCompanyName + ".projectTable(projectID int(11) NOT NULL AUTO_INCREMENT,projectName varchar(45) DEFAULT NULL UNIQUE,projectVersion varchar(10), PRIMARY KEY (projectID)) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+ newCompanyName + ".projectAssign(userID int(11) NOT NULL , PRIMARY KEY (userID)) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllCompaniesNames()  {
        List<String> companyNames = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM indexDB.companiesTable");
            while (rs.next()) {
                companyNames.add(rs.getString("companyName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyNames;
    }

    public List<String> getAllCompaniesIDs()  {
        List<String> companyIDs = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM indexDB.companiesTable");
            while (rs.next()) {
                companyIDs.add(rs.getString("companyID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyIDs;
    }

    public boolean isFieldUnique(String inputValue, String inputName) {
        boolean isUnique = false;
        try {
            connection= DB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM indexdb.companiesTable WHERE "+inputName+"=?");
            preparedStatement.setString(1 , inputValue);
            ResultSet rs = preparedStatement.executeQuery();
            if ( !rs.next() ) {
                isUnique = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUnique;
    }
}