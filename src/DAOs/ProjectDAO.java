package DAOs;

import Beans.*;
import DB.UserDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {
    private Connection connection;

    public ProjectDAO() {
        connection = UserDB.getConnection();
    }

    public void addProject( String companyName , ProjectBean project)  {
        try {
            String projectID = null;
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO "+ companyName +".projecttable(projectName , projectVersion) VALUES (?,?)");
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getProjectVersion());
            preparedStatement.executeUpdate();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT max(projectID) FROM "+ companyName +".projecttable ");
            int newProjectID = 0;
            if(rs.next()){
                projectID = "projectId" + rs.getInt(1);
            }
            statement.executeUpdate("ALTER TABLE " + companyName + ".projectassign ADD `" + projectID + "` BOOLEAN NOT NULL DEFAULT false");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProject( String companyName , int projectId ) {
        String projectIDString = "projectId" + projectId;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM "+companyName+".projecttable WHERE projectID=?");

            preparedStatement.setInt(1, projectId);
            preparedStatement.executeUpdate();

            Statement statement = connection.createStatement();
            statement.executeUpdate("ALTER TABLE "+companyName+".projectassign DROP COLUMN "+projectIDString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProjectBean> getAllProjectsFromCompany(String companyName) {
        List<ProjectBean> projects = new ArrayList<ProjectBean>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM "+companyName+".projecttable");
            while (rs.next()) {
                ProjectBean project = new ProjectBean();
                project.setProjectID(rs.getInt("ProjectID"));
                project.setProjectName(rs.getString("ProjectName"));
                project.setProjectVersion(rs.getString("ProjectVersion"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }
}
