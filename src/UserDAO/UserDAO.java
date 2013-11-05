package UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import UserBean.*;
import UserDB.*;

public class UserDAO {

    private Connection connection;

    public UserDAO() {
        connection = UserDB.getConnection();
    }

    public void addUser(UserBean user) {
        System.out.println("in the DAO");
        try {
            PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO `users-table`(`user-name`,`first-name`,`last-name`,`user-email`, `user-salt`,`user-password`) VALUES (? , ? , ? , ? , ? , ?  )");
            // Parameters start with 1
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getUseremail());
            preparedStatement.setString(5, user.getUsersalt());
            preparedStatement.setString(6, user.getUserpassword());
            System.out.println("in the DAO: " + user.getUserpassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM `users-table` WHERE `user-id`=?");
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
                    .prepareStatement("UPDATE `users-table` SET `user-name`=?, `first-name`=?, `last-name`=?,  `user-email`=?, `user-salt`=?, `user-password`=?, `is-handler`=?,`is-manager`=?,`is-reporter`=?, WHERE `user-id`=?");
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
        }
    }

    public List<UserBean> getAllUsers() {
        List<UserBean> users = new ArrayList<UserBean>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `users-table`");
            while (rs.next()) {
                UserBean user = new UserBean();
                user.setUserid(rs.getInt("user-id"));
                user.setUsername(rs.getString("user-name"));
                user.setFirstname(rs.getString("first-name"));
                user.setLastname(rs.getString("last-name"));
                user.setUseremail(rs.getString("user-email"));
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
                    prepareStatement("SELECT * FROM `users-table` WHERE `user-id`=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserid(rs.getInt("user-id"));
                user.setUsername(rs.getString("user-name"));
                user.setFirstname(rs.getString("first-name"));
                user.setLastname(rs.getString("last-name"));
                user.setUseremail(rs.getString("user-email"));;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}