package DAOs;

import Beans.*;
import DB.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    private Connection connection;

    public CompanyDAO() {
    }

    public void addCompany(CompanyBean company)  {

        connection= CompanyDB.getConnection();

        try {
            //check if index database exists and creates one if NOT
            ResultSet rs = null;
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS indexDB;");
            statement.executeUpdate("use indexDB;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS indexDB.companiesTable(companyID int(11) NOT NULL AUTO_INCREMENT,companyName varchar(45) DEFAULT NULL UNIQUE, PRIMARY KEY (companyID)) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;");

            ResultSet existingUser = statement.executeQuery("SELECT * FROM FROM indexDB.companiesTable WHERE companyName = ''");

            //add new company to the index database
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO indexDB.companiesTable(companyName) VALUES (?)");
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.executeUpdate();

            //get new company ID
            int newCompanyID = 0;
            rs = statement.executeQuery("SELECT MAX(companyID) FROM indexDB.companiesTable");
            if (rs.next()) {newCompanyID = rs.getInt(1);}
            statement.executeUpdate("GRANT ALL PRIVILEGES ON *.* TO admin@localhost WITH GRANT OPTION");
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS company" + newCompanyID);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllCompanies()  {
        connection = CompanyDB.getConnection();
        List<String> companies = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS indexDB;");
            statement.executeUpdate("use indexDB;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS indexDB.companiesTable(companyID int(11) NOT NULL AUTO_INCREMENT,companyName varchar(45) DEFAULT NULL UNIQUE, PRIMARY KEY (companyID)) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;");

            ResultSet rs = statement.executeQuery("SELECT * FROM indexDB.companiesTable");
            while (rs.next()) {
                companies.add(rs.getString("companyName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

}