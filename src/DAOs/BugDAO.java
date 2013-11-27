package DAOs;

import DB.DB;

import java.sql.*;
import Beans.BugBean;

public class BugDAO {
    private Connection connection;

    public BugDAO() {
        connection= DB.getConnection();
    }

    //TODO REMEMBER, this method is horsepucky , SO IMPLEMENT IT !!!
    public void addBug( String companyName , BugBean bug)  {
        try {
            String projectID = null;
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO "+ companyName +".bugtable(bugTitle) VALUES (?)");
            preparedStatement.setString(1, bug.getBugTitle());
            preparedStatement.executeUpdate();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT max(projectID) FROM "+ companyName +".projecttable ");

            if(rs.next()){
                projectID = "" + rs.getInt(1);
            }
            statement.executeUpdate("ALTER TABLE " + companyName + ".projectassign ADD `" + projectID + "` BOOLEAN NOT NULL DEFAULT false");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}