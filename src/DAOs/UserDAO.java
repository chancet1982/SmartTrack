package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Beans.*;
import DB.*;

public class UserDAO {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public UserDAO() {
        connection = DB.getConnection();
    }

    public void addUser(UserBean user)  {
        try {
            //Creating database if not there...
            statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS indexDB;");
            //statement.executeUpdate("use indexDB;");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS indexDB.usersTable(id int(11) NOT NULL AUTO_INCREMENT,`companyName` varchar(45) DEFAULT NULL,`firstName` varchar(45) DEFAULT NULL,`lastName` varchar(45) DEFAULT NULL,`userEmail` varchar(100) DEFAULT NULL,`userPassword` varchar(105) DEFAULT NULL,`handler` int(1) NOT NULL DEFAULT 0,`manager` int(1) NOT NULL DEFAULT 0,`reporter` int(1) NOT NULL DEFAULT 0, PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;");

            //Add user to index database
            preparedStatement = connection.prepareStatement("INSERT INTO indexdb.usersTable(companyName , firstName , lastName , userEmail , userPassword) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, user.getCompanyName());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getUseremail());
            preparedStatement.setString(5, user.getUserpassword());
            preparedStatement.executeUpdate();

            //Add to projectAssign table
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT max(id) FROM indexdb.usersTable");
            int newUserID = 1;
            if(rs.next()){newUserID = rs.getInt(1);}
            statement.executeUpdate("INSERT INTO " + user.getCompanyName() + ".projectassign (userID) VALUES (" + newUserID + ")");
            statement.executeUpdate("INSERT INTO " + user.getCompanyName() + ".bugsassign (userID) VALUES (" + newUserID + ")");

            statement.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM indexdb.usersTable WHERE id=?");
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(UserBean user) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE indexdb.usersTable SET firstName=?, lastName=?, userEmail=?, userPassword=?, handler=?, manager=?, reporter=? WHERE id=?");
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getUseremail());
            preparedStatement.setString(4, user.getUserpassword());
            preparedStatement.setBoolean(5, user.isIshandler());
            preparedStatement.setBoolean(6, user.isIsmanager());
            preparedStatement.setBoolean(7, user.isIsreporter());
            preparedStatement.setInt(8, user.getUserid());
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UserBean> getAllUsers() {
        List<UserBean> users = new ArrayList<UserBean>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM indexdb.usersTable");
            while (rs.next()) {
                UserBean user = new UserBean();
                user.setUserid(rs.getInt("id"));
                user.setFirstname(rs.getString("firstName"));
                user.setLastname(rs.getString("lastName"));
                user.setUseremail(rs.getString("userEmail"));
                users.add(user);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<UserBean> getAllUsersFromCompany(String companyName) {
        List<UserBean> users = new ArrayList<UserBean>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM indexdb.usersTable WHERE companyName='" + companyName + "'");
            while (rs.next()) {
                UserBean user = new UserBean();
                //String initials = rs.getString("firstName").substring(0,1) + rs.getString("lastName").substring(0,1);
                rs.getString("lastName");
                user.setUserid(rs.getInt("id"));
                user.setFirstname(rs.getString("firstName"));
                user.setLastname(rs.getString("lastName"));
                user.setUseremail(rs.getString("userEmail"));
                users.add(user);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public UserBean getUserByID(int userID) {
        UserBean user = new UserBean();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM indexdb.usersTable WHERE id=?");
            preparedStatement.setInt(1, userID);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserid(rs.getInt("id"));
                user.setUserpassword(rs.getString("userPassword"));
                user.setFirstname(rs.getString("firstName"));
                user.setLastname(rs.getString("lastName"));
                user.setUseremail(rs.getString("userEmail"));
                user.setIshandler(rs.getBoolean("handler"));
                user.setIsmanager(rs.getBoolean("manager"));
                user.setIsreporter(rs.getBoolean("reporter"));
            }

            rs.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public UserBean getUserByEmail(String email) {
        UserBean user = new UserBean();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM indexdb.usersTable WHERE userEmail=?");
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserid(rs.getInt("id"));
                user.setCompanyName(rs.getString("companyName"));
                user.setFirstname(rs.getString("firstName"));
                user.setLastname(rs.getString("lastName"));
                user.setUseremail(rs.getString("userEmail"));
                user.setUserpassword(rs.getString("userPassword"));
            }

            rs.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean isFieldUnique(String inputValue, String inputName) {
        boolean isUnique = false;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SHOW DATABASES LIKE 'indexdb'");

            if (rs.next()) {
                preparedStatement = connection.prepareStatement("SELECT * FROM indexdb.usersTable WHERE "+inputName+"=?");
                preparedStatement.setString(1 , inputValue);

                rs = preparedStatement.executeQuery();
                if ( !rs.next() ) {
                    isUnique = true;
                }

                preparedStatement.close();

            } else {
                isUnique = true;
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isUnique;
    }
}