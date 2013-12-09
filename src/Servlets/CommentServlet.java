package Servlets;

import Beans.CommentBean;
import DAOs.CommentDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CommentServlet extends HttpServlet{

    CommentDAO commentDAO;

    private static String LIST_COMMENTS = "/listProjects.jsp";
    private static String INSERT_COMMENT = "/ProjectServlet?action=listComments";

    public CommentServlet(){
        super();
        commentDAO = new CommentDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        //get company name from cookie
        Cookie[] cookies ;
        String companyName = null;
        int bugID = 0;
        //TODO get bug ID dynamically
        cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("cid")){ companyName = cookie.getValue(); }
        }

        if (action.equalsIgnoreCase("delete")){  //Delete Single
            int commentID = Integer.parseInt(request.getParameter("commentID"));
            commentDAO.deleteComment(companyName, commentID);
            forward = LIST_COMMENTS;
            request.setAttribute("comments", commentDAO.getAllCommentsForBug(companyName , bugID));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } else if (action.equalsIgnoreCase("listComments")){ //List All
            forward = LIST_COMMENTS;
            request.setAttribute("comments", commentDAO.getAllCommentsForBug(companyName , bugID));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else {
            forward = "";
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentBean comment = new CommentBean();
        comment.setCommentContent(request.getParameter("commentContent"));

        //get company name + userID from cookies
        Cookie[] cookies ;
        String companyName = null;
        int userID = 0;
        cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("cid")){ companyName = cookie.getValue(); }
            if(cookie.getName().equals("uid")){  userID = Integer.parseInt(cookie.getValue()); }
        }

        commentDAO.addComment( companyName , comment, userID, userID );
        response.sendRedirect(INSERT_COMMENT);
        //TODO figure out a way to retrieve the bug ID
    }
}
