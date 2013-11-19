package DAOs;

import Beans.*;
import DB.UserDB;

import java.sql.*;

public class ProjectDAO {
    private Connection connection;

    public ProjectDAO() {
        connection = UserDB.getConnection();
    }

    public void addProject( String companyName , ProjectBean project)  {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO "+ companyName +".projecttable(projectName , projectVersion) VALUES (?,?)");
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getProjectVersion());
            preparedStatement.executeUpdate();


            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT max(projectID) FROM "+ companyName +".projecttable ");
            int newProjectID = 0;
            if(rs.next()){
                newProjectID = rs.getInt(1);
            }
            statement.executeUpdate("ALTER TABLE " + companyName + ".projectassign ADD `" + newProjectID + "` int(10)");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
