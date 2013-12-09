package DAOs;

import Beans.CommentBean;
import Beans.ProjectBean;
import Beans.UserBean;
import DB.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public CommentDAO() {
        connection = DB.getConnection();
    }

    public void addComment( String companyName , CommentBean comment)  {
        System.out.println("Should create comment (servlet):");
        System.out.println("comment: " + comment.getCommentContent());
        System.out.println("bugID: " + comment.getCommentUserID());
        System.out.println("userID: " + comment.getCommentBugID());

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO "+ companyName +".commentstable(commentContent , userID, bugID) VALUES (?,?,?)");
            preparedStatement.setString(1, comment.getCommentContent());
            preparedStatement.setString(2, comment.getCommentUserID());
            preparedStatement.setInt(3, comment.getCommentBugID());
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteComment( String companyName , int commentID ) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM "+companyName+".commentstable WHERE commentID=?");
            preparedStatement.setInt(1, commentID);
            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CommentBean> getAllCommentsForBug(String companyName , int bugID) {
        List<CommentBean> comments = new ArrayList<CommentBean>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM "+companyName+".commentstable WHERE bugID=?");
            preparedStatement.setInt(1, bugID);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                CommentBean comment = new CommentBean();
                comment.setCommentID(rs.getInt("commentID"));
                comment.setCommentContent(rs.getString("commentContent"));
                comment.setCommentUserID(rs.getString("userID"));
                comment.setCreated(rs.getString("created"));
                comments.add(comment);
            }

            rs.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

}
