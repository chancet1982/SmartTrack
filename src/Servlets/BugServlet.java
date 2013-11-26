package Servlets;

import Beans.BugBean;
import Beans.ProjectBean;
import DAOs.BugDAO;
import DAOs.ProjectDAO;
import DAOs.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BugServlet extends HttpServlet {

    BugDAO bugDAO;
    private static String LIST_BUGS = "/listProjects.jsp";
    private static String ASSIGN_BUGS = "/assignUsersToProjects.jsp";

    public BugServlet(){
        super();
        bugDAO = new BugDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BugBean bug = new BugBean();
        bug.setBugTitle(request.getParameter("bugTitle"));
        bug.setBugCategory(request.getParameter("bugCategory"));
        bug.setBugDescription(request.getParameter("bugDescription"));
        bug.setBugStatus(request.getParameter("bugStatus"));
        bug.setReportedPriority(request.getParameter("reportedPriority"));
        bug.setBugURL(request.getParameter("bugURL"));
        bug.setScreenshotURL(request.getParameter("screenshotURL"));
        bug.setBugPCInfo(request.getParameter("bugPCInfo"));
        bug.setBugErrorCode(request.getParameter("bugErrorCode"));
        bug.setTextFromTo(request.getParameter("textFromTo"));

        //get company name from cookie
        Cookie[] cookies ;
        String companyName = null;
        cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("cid")){ companyName = cookie.getValue(); }
        }
        System.out.println("project has been added");
        bugDAO.addBug(companyName, bug);
        response.sendRedirect("afterLogin.jsp");
    }

}
