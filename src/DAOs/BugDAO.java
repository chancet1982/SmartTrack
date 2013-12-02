package DAOs;

import Beans.ProjectBean;
import Beans.UserBean;
import DB.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Beans.BugBean;

public class BugDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public BugDAO() {
        connection= DB.getConnection();
    }

    public void addBug( String companyName , BugBean bug)  {
        try {
            String bugID = null;

            preparedStatement =
            connection.prepareStatement("INSERT INTO "+ companyName +
                    ".bugsTable(bugCategory,bugTitle,bugDescription,bugStatus,reportedPriority,bugURL,screenshotURL,bugPCInfo,bugErrorCode,textFromTo,stepsToRecreate,bugTimeStamp,projectID) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1, bug.getBugCategory());
            preparedStatement.setString(2, bug.getBugTitle());
            preparedStatement.setString(3, bug.getBugDescription());
            preparedStatement.setString(4, "unassigned");
            preparedStatement.setString(5, bug.getReportedPriority());
            preparedStatement.setString(6, bug.getBugURL());
            preparedStatement.setString(7, bug.getScreenshotURL());
            preparedStatement.setString(8, bug.getBugPCInfo());
            preparedStatement.setString(9, bug.getBugErrorCode());
            preparedStatement.setString(10, bug.getTextFromTo());
            preparedStatement.setString(11, bug.getStepsToRecreate());
            preparedStatement.setString(12, bug.getBugTimeStamp());
            preparedStatement.setInt(13, bug.getProjectID());
            preparedStatement.executeUpdate();

            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT max(bugID) FROM "+ companyName +".bugsTable ");

            if(rs.next()){
                bugID = "" + rs.getInt(1);
            }

            statement.executeUpdate("ALTER TABLE " + companyName + ".bugsAssign ADD `" + bugID + "` BOOLEAN NOT NULL DEFAULT false");

            rs.close();
            statement.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBug( String companyName , int bugID ) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM " + companyName + ".bugsTable WHERE bugID=?");
            preparedStatement.setInt(1, bugID);
            preparedStatement.executeUpdate();

            statement = connection.createStatement();
            statement.executeUpdate("ALTER TABLE "+companyName+".bugsAssign DROP COLUMN `" + bugID + "`");

            statement.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BugBean> getAllBugsFromCompany(String companyName) {
        List<BugBean> bugs = new ArrayList<BugBean>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM "+companyName+".bugstable");

            while (rs.next()) {
                BugBean bug = new BugBean();
                bug.setBugID(rs.getInt("bugID"));
                bug.setBugTitle(rs.getString("bugTitle"));
                bug.setBugTitle(rs.getString("bugDescription"));
                bugs.add(bug);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bugs;
    }

    public List<UserBean> getUsersAssigned(String companyName , int bugID) {
        List<UserBean> users = new ArrayList<UserBean>();
        UserDAO userDAO = new UserDAO();

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM "+companyName+".bugsAssign WHERE `"+ bugID +"`='1'");

            while (rs.next()) {
                UserBean user = new UserBean();
                user = userDAO.getUserById(rs.getInt("userID"));
                users.add(user);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void assignUser(String companyName , int bugID , int userID){
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE "+ companyName +".bugsAssign SET `"+bugID+"`='1' WHERE `userID`='"+ userID +"'");

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void emptyBugAssignment(String companyName , int bugID ){
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE "+ companyName +".`bugsAssign` SET `"+bugID+"`='0'");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}