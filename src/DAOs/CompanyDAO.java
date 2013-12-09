package DAOs;

import Beans.*;
import DB.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public CompanyDAO() {
        connection= DB.getConnection();
    }

    public void addCompany(CompanyBean company)  {
        try {
            statement = connection.createStatement();

            String newCompanyName = company.getCompanyName();

            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + newCompanyName);
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+ newCompanyName + ".projectTable(projectID int(11) NOT NULL AUTO_INCREMENT,projectName varchar(45) DEFAULT NULL,projectVersion varchar(10), created timestamp DEFAULT 0, modified timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (projectID)) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+ newCompanyName + ".projectAssign(userID int(11) NOT NULL , PRIMARY KEY (userID)) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+ newCompanyName + ".bugsTable(bugID int(11) NOT NULL AUTO_INCREMENT, projectID int(11) DEFAULT NULL, bugCategory varchar(45) DEFAULT NULL, bugTitle varchar(45) DEFAULT NULL, bugDescription varchar(400) DEFAULT NULL,bugStatus varchar(45) DEFAULT NULL,bugPriority int(1) DEFAULT NULL,reportedPriority varchar(45) DEFAULT NULL,bugURL varchar(100) DEFAULT NULL,screenshotURL varchar(100) DEFAULT NULL,bugPCInfo varchar(100) DEFAULT NULL,bugErrorCode varchar(45) DEFAULT NULL,textFromTo varchar(45) DEFAULT NULL,stepsToRecreate varchar(1000) DEFAULT NULL, bugTimeStamp varchar(20)DEFAULT NULL, created timestamp DEFAULT 0, modified timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (bugID)) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+ newCompanyName + ".bugsAssign(userID int(11) NOT NULL , PRIMARY KEY (userID)) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+ newCompanyName + ".commentsTable(commentID int(11) NOT NULL AUTO_INCREMENT, commentContent varchar(500) DEFAULT NULL, userID varchar(45) NOT NULL, bugID int(11) NOT NULL, created timestamp DEFAULT 0, modified timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (commentID)) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;");

            statement.executeUpdate("USE "+newCompanyName);
            statement.executeUpdate("CREATE TRIGGER projectTableInsertTrigger BEFORE INSERT ON "+ newCompanyName + ".projectTable FOR EACH ROW BEGIN IF NEW.created = '0000-00-00 00:00:00' THEN SET NEW.created = NOW(); END IF; END;");
            statement.executeUpdate("CREATE TRIGGER bugsTableInsertTrigger BEFORE INSERT ON "+ newCompanyName + ".bugsTable FOR EACH ROW BEGIN IF NEW.created = '0000-00-00 00:00:00' THEN SET NEW.created = NOW(); END IF; END;");
            statement.executeUpdate("CREATE TRIGGER commentsTableInsertTrigger BEFORE INSERT ON "+ newCompanyName + ".commentsTable FOR EACH ROW BEGIN IF NEW.created = '0000-00-00 00:00:00' THEN SET NEW.created = NOW(); END IF; END;");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<String> getAllCompaniesNames()  {
        List<String> companyNames = new ArrayList<String>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM indexDB.companiesTable");

            while (rs.next()) {
                companyNames.add(rs.getString("companyName"));
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyNames;
    }

    public List<String> getAllCompaniesIDs()  {
        List<String> companyIDs = new ArrayList<String>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM indexDB.companiesTable");

            while (rs.next()) {
                companyIDs.add(rs.getString("companyID"));
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyIDs;
    }

    public boolean isFieldUnique(String inputValue, String inputName) {
        boolean isUnique = false;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SHOW DATABASES LIKE 'indexdb'");
            if (rs.next() ) {
                rs.close();

                preparedStatement = connection.prepareStatement("SELECT * FROM indexdb.companiesTable WHERE "+inputName+"=?");
                preparedStatement.setString(1 , inputValue);
                rs = preparedStatement.executeQuery();
                preparedStatement.close();

                if ( !rs.next() ) {
                    isUnique = true;
                }
            } else {
                isUnique = true;
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUnique;
    }
}