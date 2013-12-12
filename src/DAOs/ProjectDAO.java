package DAOs;

import Beans.*;
import DB.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public ProjectDAO() {
        connection = DB.getConnection();
    }

    public void addProject( String companyName , ProjectBean project)  {
        try {
            String projectID = null;
            preparedStatement = connection.prepareStatement("INSERT INTO "+ companyName +".projecttable(projectName , projectVersion, startDate , endDate) VALUES (?,?,?,?)");
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getProjectVersion());
            preparedStatement.setString(3, project.getStartDate());
            preparedStatement.setString(4, project.getEndDate());
            preparedStatement.executeUpdate();

            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT max(projectID) FROM "+ companyName +".projecttable ");

            if(rs.next()){
                projectID = "" + rs.getInt(1);
            }
            statement.executeUpdate("ALTER TABLE " + companyName + ".projectassign ADD `" + projectID + "` BOOLEAN NOT NULL DEFAULT false");

            rs.close();
            statement.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProject( String companyName , int projectID ) {

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM "+companyName+".projecttable WHERE projectID=?");
            preparedStatement.setInt(1, projectID);
            preparedStatement.executeUpdate();

            statement = connection.createStatement();
            statement.executeUpdate("ALTER TABLE "+companyName+".projectassign DROP COLUMN `"+projectID+"`");

            statement.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProjectBean> getAllProjectsFromCompany(String companyName) {
        List<ProjectBean> projects = new ArrayList<ProjectBean>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM "+companyName+".projecttable");

            while (rs.next()) {
                ProjectBean project = new ProjectBean();
                project.setProjectID(rs.getInt("ProjectID"));
                project.setProjectName(rs.getString("ProjectName"));
                project.setProjectVersion(rs.getString("ProjectVersion"));
                projects.add(project);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public List<UserBean> getUsersAssigned(String companyName , int projectID) {
        List<UserBean> users = new ArrayList<UserBean>();
        UserDAO userDAO = new UserDAO();

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM "+companyName+".projectassign WHERE `"+ projectID +"`='1'");

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

    public void assignUser(String companyName , int projectID , int userID){
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE "+ companyName +".`projectassign` SET `"+projectID+"`='1' WHERE `userID`='"+ userID +"'");

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void emptyProjectAssignment(String companyName , int projectID ){
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE "+ companyName +".`projectassign` SET `"+projectID+"`='0'");

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
