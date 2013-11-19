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

    public UserDAO() {
        connection = UserDB.getConnection();
    }

    public void addUser(UserBean user)  {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO indexdb.usersTable(companyName , firstName , lastName , userEmail , userPassword) VALUES (?,?,?,?,?)");

            // Parameters start with 1
            System.out.println( user.getUserpassword() );
            preparedStatement.setString(1, user.getCompanyName());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getUseremail());
            preparedStatement.setString(5, user.getUserpassword());
            preparedStatement.executeUpdate();

            //add to projectAssign table
            //TODO For SAM: test this with the email invite
            //TODO For MN: fix this shit!
            System.out.print("USERID- " + user.getUserid());
            connection.prepareStatement("INSERT INTO " + user.getCompanyName() + ".projectassign (userID) VALUES (?)" );
            preparedStatement.setInt(1, user.getUserid());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
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
                    .prepareStatement("UPDATE indexdb.usersTable SET firstName=?, lastName=?, userEmail=?, userPassword=?, handler=?, manager=?, reporter=? WHERE id=?");
            // Parameters start with 1
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getUseremail());
            preparedStatement.setString(4, user.getUserpassword());
            preparedStatement.setBoolean(5, user.isIshandler());
            preparedStatement.setBoolean(6, user.isIsmanager());
            preparedStatement.setBoolean(7, user.isIsreporter());
            preparedStatement.setInt(8, user.getUserid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
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

    public List<UserBean> getAllUsersFromCompany(String companyName) {
        List<UserBean> users = new ArrayList<UserBean>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM indexdb.usersTable WHERE companyName='" + companyName + "'");
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
                user.setUserid(rs.getInt("id"));
                user.setUserpassword(rs.getString("userPassword"));
                user.setFirstname(rs.getString("firstName"));
                user.setLastname(rs.getString("lastName"));
                user.setUseremail(rs.getString("userEmail"));
                user.setIshandler(rs.getBoolean("handler"));
                user.setIsmanager(rs.getBoolean("manager"));
                user.setIsreporter(rs.getBoolean("reporter"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public UserBean getUserByEmail(String email) {
        UserBean user = new UserBean();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM indexdb.usersTable WHERE userEmail=?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserid(rs.getInt("id"));
                user.setCompanyName(rs.getString("companyName"));
                user.setFirstname(rs.getString("firstName"));
                user.setLastname(rs.getString("lastName"));
                user.setUseremail(rs.getString("userEmail"));
                user.setUserpassword(rs.getString("userPassword"));
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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUnique;
    }
}