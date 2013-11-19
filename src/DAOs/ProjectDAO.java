package DAOs;

import Beans.*;
import DB.UserDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
            statement.executeQuery("ALTER TABLE "+companyName+".projecttable ADD "+ project.getProjectName()+ " VARCHAR(200)");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
