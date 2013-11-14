package DAOs;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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

    public UserDAO() {
        connection = UserDB.getConnection();
    }

    public void addUser(UserBean user)  {
        try {

            System.out.println("connection is: " + connection);
            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO indexdb.usersTable(companyID , firstName , lastName , userEmail , userPassword) VALUES (?,?,?,?,?)");

            // Parameters start with 1
            System.out.println( user.getUserpassword() );
            preparedStatement.setString(1, user.getCompanyID());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getUseremail());
            preparedStatement.setString(5, user.getUserpassword());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM indexdb.usersTable WHERE id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(UserBean user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE indexdb.usersTable SET `user-name`=?, `first-name`=?, `last-name`=?,  `user-email`=?, `user-salt`=?, `user-password`=?, `is-handler`=?,`is-manager`=?,`is-reporter`=?, WHERE `user-id`=?");
            // Parameters start with 1
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getUseremail());
            preparedStatement.setString(5, user.getUsersalt());
            preparedStatement.setString(6, user.getUserpassword());
            preparedStatement.setBoolean(7, user.isIshandler());
            preparedStatement.setBoolean(8, user.isIsmanager());
            preparedStatement.setBoolean(9, user.isIsreporter());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public List<UserBean> getAllUsers() {
        List<UserBean> users = new ArrayList<UserBean>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM indexdb.usersTable");
            while (rs.next()) {
                UserBean user = new UserBean();
                user.setUserid(rs.getInt("id"));
                user.setFirstname(rs.getString("firstName"));
                user.setLastname(rs.getString("lastName"));
                user.setUseremail(rs.getString("userEmail"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public UserBean getUserById(int userId) {
        UserBean user = new UserBean();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM indexdb.usersTable WHERE id=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserid(rs.getInt("user-id"));
                user.setUsername(rs.getString("user-name"));
                user.setFirstname(rs.getString("first-name"));
                user.setLastname(rs.getString("last-name"));
                user.setUseremail(rs.getString("user-email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

        public boolean isFieldUnique(String inputValue, String inputName) {
        boolean isUnique = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM indexdb.usersTable WHERE "+inputName+"=?");
            preparedStatement.setString(1 , inputValue);
            ResultSet rs = preparedStatement.executeQuery();
            if ( !rs.next() ) {
                isUnique = true;
            }else{
                isUnique = false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUnique;
    }
}