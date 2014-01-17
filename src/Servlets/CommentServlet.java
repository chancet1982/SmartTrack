package Servlets;

import Beans.CommentBean;
import Beans.ProjectBean;
import Beans.UserBean;
import DAOs.BugDAO;
import DAOs.CommentDAO;
import DAOs.ProjectDAO;
import DAOs.UserDAO;

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
    UserDAO userDAO;

    private static String LIST_COMMENTS = "/listProjects.jsp";
    private static String INSERT_COMMENT = "/BugServlet?action=ListBugs&message-success=Comment added successfully";

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

        cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("cid")){ companyName = cookie.getValue(); }
        }

        if (action.equalsIgnoreCase("delete")){  //Delete Single
            /*int commentID = Integer.parseInt(request.getParameter("commentID"));
            comment.deleteComment(companyName, commentID);
            forward = LIST_COMMENTS;
            request.setAttribute("comments", comment.getAllCommentsForBug(companyName , bugID));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);*/
        } else if (action.equalsIgnoreCase("listComments")){ //List All
            bugID = Integer.parseInt(request.getParameter("bugID"));
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            List<CommentBean> comments = commentDAO.getAllCommentsForBug(companyName, bugID) ;

            for(int i=0; i<comments.size(); i++){
                System.out.print("Email: " + comments.get(i).getCommentUserID());
                //TODO get user initials
                //System.out.print(user.getInitials());
                //UserBean user = userDAO.getUserByEmail(comments.get(i).getCommentUserID());
                if (i % 2 == 0) {
                    // odd
                    out.write("<li class='comment odd clearfix'>"+
                            "<span class='user'>SM</span>"+
                            "<span class='content'>"+ comments.get(i).getCommentContent() +"</span></li>");
                } else {
                    // even
                    out.write("<li class='comment even clearfix'>"+
                            "<span class='user'>SM</span>"+
                            "<span class='content'>"+ comments.get(i).getCommentContent() +"</span></li>");
                }
            }
        } else if (action.equalsIgnoreCase("createComment")){ //List All

        } else {
            forward = "";
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get company name from cookie
        Cookie[] cookies ;
        String companyName = null;
        String userID = null;
        int bugID = 0;
        cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("cid")){ companyName = cookie.getValue(); }
            if(cookie.getName().equals("uid")){  userID = cookie.getValue(); }
        }

        System.out.println("Should create comment (servlet):");
        System.out.println("comment: " + request.getParameter("commentContent"));
        System.out.println("bugID: " + request.getParameter("bugID"));
        System.out.println("userID: " + request.getParameter("userID"));
        CommentBean comment = new CommentBean();

        comment.setCommentContent(request.getParameter("commentContent"));
        comment.setCommentBugID(Integer.parseInt(request.getParameter("bugID")));
        comment.setCommentUserID(request.getParameter("userID"));

        commentDAO.addComment( companyName , comment);
        response.sendRedirect(INSERT_COMMENT);
    }
}
