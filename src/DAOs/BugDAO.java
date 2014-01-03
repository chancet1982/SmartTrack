package DAOs;

import Beans.BugBean;
import Beans.UserBean;
import DB.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public BugBean getBugByID(String companyName , int bugID) {
        BugBean bug = new BugBean();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + companyName + ".bugsTable WHERE bugID=?");
            preparedStatement.setInt(1, bugID);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                bug.setBugID(rs.getInt("bugID"));
                bug.setProjectID(rs.getInt("projectID"));
                bug.setBugCategory(rs.getString("bugCategory"));
                bug.setBugTitle(rs.getString("bugTitle"));
                bug.setBugDescription(rs.getString("bugDescription"));
                bug.setBugStatus(rs.getString("bugStatus"));
                bug.setBugPriority(rs.getString("bugPriority"));
                bug.setReportedPriority(rs.getString("reportedPriority"));
                bug.setBugURL(rs.getString("bugURL"));
                bug.setScreenshotURL(rs.getString("screenshotURL"));
                bug.setBugPCInfo(rs.getString("bugPCInfo"));
                bug.setBugErrorCode(rs.getString("bugErrorCode"));
                bug.setStepsToRecreate(rs.getString("stepsToRecreate"));
                bug.setBugTimeStamp(rs.getString("bugTimeStamp"));
                bug.setCreated(String.format("%1$TD %1$TT", rs.getTimestamp("created")));
                bug.setModified(String.format("%1$TD %1$TT", rs.getTimestamp("modified")));
                bug.setActive(rs.getBoolean("active"));
            }

            rs.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bug;
    }

    public List<BugBean> getAllBugsFromCompany(String companyName) {
        List<BugBean> bugs = new ArrayList<BugBean>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM "+companyName+".bugsTable");

            while (rs.next()) {
                BugBean bug = new BugBean();
                bug.setBugID(rs.getInt("bugID"));
                bug.setProjectID(rs.getInt("projectID"));
                bug.setBugCategory(rs.getString("bugCategory"));
                bug.setBugTitle(rs.getString("bugTitle"));
                bug.setBugDescription(rs.getString("bugDescription"));
                bug.setBugStatus(rs.getString("bugStatus"));
                bug.setBugPriority(rs.getString("bugPriority"));
                bug.setReportedPriority(rs.getString("reportedPriority"));
                bug.setBugURL(rs.getString("bugURL"));
                bug.setScreenshotURL(rs.getString("screenshotURL"));
                bug.setBugPCInfo(rs.getString("bugPCInfo"));
                bug.setBugErrorCode(rs.getString("bugErrorCode"));
                bug.setStepsToRecreate(rs.getString("stepsToRecreate"));
                bug.setBugTimeStamp(rs.getString("bugTimeStamp"));
                bug.setCreated(String.format("%1$TD %1$TT", rs.getTimestamp("created")));
                bug.setModified(String.format("%1$TD %1$TT", rs.getTimestamp("modified")));
                bug.setActive(rs.getBoolean("active"));
                bugs.add(bug);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bugs;
    }

    public List<BugBean> getAllBugsForProject(String companyName, int projectID) {
        List<BugBean> bugs = new ArrayList<BugBean>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + companyName + ".bugsTable WHERE projectID=?");
            preparedStatement.setInt(1, projectID);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                BugBean bug = new BugBean();
                bug.setBugID(rs.getInt("bugID"));
                bug.setProjectID(rs.getInt("projectID"));
                bug.setBugCategory(rs.getString("bugCategory"));
                bug.setBugTitle(rs.getString("bugTitle"));
                bug.setBugDescription(rs.getString("bugDescription"));
                bug.setBugStatus(rs.getString("bugStatus"));
                bug.setBugPriority(rs.getString("bugPriority"));
                bug.setReportedPriority(rs.getString("reportedPriority"));
                bug.setBugURL(rs.getString("bugURL"));
                bug.setScreenshotURL(rs.getString("screenshotURL"));
                bug.setBugPCInfo(rs.getString("bugPCInfo"));
                bug.setBugErrorCode(rs.getString("bugErrorCode"));
                bug.setStepsToRecreate(rs.getString("stepsToRecreate"));
                bug.setBugTimeStamp(rs.getString("bugTimeStamp"));
                bug.setCreated(String.format("%1$TD %1$TT", rs.getTimestamp("created")));
                bug.setModified(String.format("%1$TD %1$TT", rs.getTimestamp("modified")));
                bug.setActive(rs.getBoolean("active"));
                bugs.add(bug);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bugs;
    }

    public List<BugBean> getAllActiveBugsForProject(String companyName, int projectID) {
        List<BugBean> bugs = new ArrayList<BugBean>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + companyName + ".bugsTable WHERE projectID=? AND active=1");
            preparedStatement.setInt(1, projectID);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                BugBean bug = new BugBean();
                bug.setBugID(rs.getInt("bugID"));
                bug.setProjectID(rs.getInt("projectID"));
                bug.setBugCategory(rs.getString("bugCategory"));
                bug.setBugTitle(rs.getString("bugTitle"));
                bug.setBugDescription(rs.getString("bugDescription"));
                bug.setBugStatus(rs.getString("bugStatus"));
                bug.setBugPriority(rs.getString("bugPriority"));
                bug.setReportedPriority(rs.getString("reportedPriority"));
                bug.setBugURL(rs.getString("bugURL"));
                bug.setScreenshotURL(rs.getString("screenshotURL"));
                bug.setBugPCInfo(rs.getString("bugPCInfo"));
                bug.setBugErrorCode(rs.getString("bugErrorCode"));
                bug.setStepsToRecreate(rs.getString("stepsToRecreate"));
                bug.setBugTimeStamp(rs.getString("bugTimeStamp"));
                bug.setCreated(String.format("%1$TD %1$TT", rs.getTimestamp("created")));
                bug.setModified(String.format("%1$TD %1$TT", rs.getTimestamp("modified")));
                bug.setActive(rs.getBoolean("active"));
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
                user = userDAO.getUserByID(rs.getInt("userID"));
                users.add(user);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<UserBean> getUsersAutocomplete(String searchFor , String companyName) {
        List<UserBean> users = new ArrayList<UserBean>();
        UserDAO userDAO = new UserDAO();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM indexDB.usersTable WHERE firstName LIKE '"+ searchFor + "%' AND companyName='"+companyName+"'");
            while (rs.next()) {
                UserBean user = new UserBean();
                user.setUserid(rs.getInt("id"));
                user.setFirstname(rs.getString("firstName"));
                user.setLastname(rs.getString("lastName"));
                users.add(user);
            }

            rs = statement.executeQuery("SELECT * FROM indexDB.usersTable WHERE lastName LIKE '"+ searchFor + "%' AND companyName='"+companyName+"'");
            while (rs.next()) {
                UserBean user = new UserBean();
                user.setUserid(rs.getInt("id"));
                user.setFirstname(rs.getString("firstName"));
                user.setLastname(rs.getString("lastName"));
                boolean isUserUnique = true;
                for(int i=0;i<users.size();i++){
                    if (user.getUserid() == users.get(i).getUserid() ){
                        isUserUnique = false;
                        break;
                    }
                }
                if(isUserUnique == true){users.add(user);}
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