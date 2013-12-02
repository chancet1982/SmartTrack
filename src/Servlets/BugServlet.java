package Servlets;

import Beans.BugBean;
import Beans.ProjectBean;
import Beans.UserBean;
import DAOs.BugDAO;
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

public class BugServlet extends HttpServlet {

    BugDAO bugDAO;
    UserDAO userDAO;

    private static String LIST_BUGS = "/listBugs.jsp";
    private static String ASSIGN_BUGS = "/assignUsersToBugs.jsp";

    public BugServlet(){
        super();
        bugDAO = new BugDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        //get company name from cookie
        Cookie[] cookies ;
        String companyName = null;
        cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("cid")){ companyName = cookie.getValue(); }
        }

        if (action.equalsIgnoreCase("delete")){  //Delete Single
            int bugID = Integer.parseInt(request.getParameter("bugID"));
            bugDAO.deleteBug(companyName, bugID);
            forward = LIST_BUGS;
            request.setAttribute("bugs", bugDAO.getAllBugsFromCompany(companyName));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } else if (action.equalsIgnoreCase("listBugs")){ //List All
            forward = LIST_BUGS;
            request.setAttribute("bugs", bugDAO.getAllBugsFromCompany(companyName));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } else if (action.equalsIgnoreCase("assignBugs")){ //Assign users to bugs
            forward = LIST_BUGS;
            userDAO = new UserDAO();
            request.setAttribute("bugs", bugDAO.getAllBugsFromCompany(companyName));
            request.setAttribute("users", userDAO.getAllUsersFromCompany(companyName));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }else if (action.equalsIgnoreCase("getProjectsInDropdown")){ //get bugs in dropdown
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            ProjectDAO projectDAO = new ProjectDAO();
            List<ProjectBean> projects = projectDAO.getAllProjectsFromCompany(companyName) ;
            for(int i=0; i<projects.size(); i++){
                out.write("<option value='"+ projects.get(i).getProjectID() +"'>"+ projects.get(i).getProjectName() +"</option>");
            }

        } else if (action.equalsIgnoreCase("getAssigned")){ //get user assigned to specific bug
            response.setContentType("text/html");
            int bugID = Integer.parseInt(request.getParameter("bugID"));
            List<UserBean> users = bugDAO.getUsersAssigned(companyName , bugID);
            PrintWriter out = response.getWriter();
            for(int i=0; i<users.size(); i++){
                out.write("<li class='user ico ui-draggable' data-user-id='"+users.get(i).getUserid()+"'>"+
                        "<span class='icon user'></span>" +
                        users.get(i).getFirstname() + "</li>");
            }

        }else if (action.equalsIgnoreCase("setAssigned")){ //set users assigned to specific bug

            int bugID = Integer.parseInt(request.getParameter("bugID"));
            String[] assignedUsers = request.getParameter("assignedUsers").split(":");

            bugDAO.emptyBugAssignment(companyName , bugID);
            System.out.println("user assigned currently ");
            for(int i=1; i<assignedUsers.length; i++){
                //TODO FIGURE OUT WHY THERE'S AN EXCEPTION HERE (although program works)
                System.out.print(Integer.parseInt(assignedUsers[i]) + "\n");
                bugDAO.assignUser(companyName , bugID , Integer.parseInt(assignedUsers[i]) );
            }

            PrintWriter out = response.getWriter();


        } else {
            forward = "";
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BugBean bug = new BugBean();

        //put Steps to recreate error into one string
        String stepsToRecreate = "";
        for(int i=1;i<10;i++){
            String param = request.getParameter("stepContent" + i);
            if(param !=null){
                stepsToRecreate = stepsToRecreate + "~" +param;
            }
        }

        bug.setProjectID(Integer.parseInt(request.getParameter("projectID")));
        bug.setBugCategory(request.getParameter("bugCategory"));
        bug.setBugTitle(request.getParameter("bugTitle"));
        bug.setBugDescription(request.getParameter("bugDescription"));
        bug.setReportedPriority(request.getParameter("reportedPriority"));
        bug.setBugURL(request.getParameter("bugURL"));
        bug.setScreenshotURL(request.getParameter("screenshotURL"));
        bug.setBugTimeStamp(request.getParameter("timeStamp"));
        bug.setBugPCInfo(request.getHeader("User-Agent"));
        bug.setBugErrorCode(request.getParameter("bugErrorCode"));
        bug.setTextFromTo(request.getParameter("textFrom")+ "~" + request.getParameter("textTo"));
        bug.setStepsToRecreate(stepsToRecreate);

        //get company name from cookie
        Cookie[] cookies ;
        String companyName = null;
        cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("cid")){ companyName = cookie.getValue(); }
        }

        System.out.println("BUG has been added");
        bugDAO.addBug(companyName, bug);
        response.sendRedirect("afterLogin.jsp");
    }

}
