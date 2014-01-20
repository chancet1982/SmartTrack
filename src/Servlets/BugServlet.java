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
    private static String LIST_OPEN_BUGS = "/listOpenBugs.jsp";
    private static String LIST_BUGS_FOR_PROJECT = "/listBugsForProject.jsp";
    private static String LIST_OPEN_BUGS_FOR_PROJECT = "/listOpenBugsForProject.jsp";
    private static String ASSIGN_BUGS = "/assignUsersToBugs.jsp";
    private static String INSERT_BUG = "/BugServlet?action=listBugs&message-success=Bug created successfully";
    private static String DELETE_BUG = "/BugServlet?action=listBugs&message-success=Bug deleted";
    private static String CLOSE_BUG = "/BugServlet?action=listBugs&message-success=Bug closed";
    private static String EDIT_BUG = "/editBug.jsp";

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
            forward = DELETE_BUG;
            request.setAttribute("bugs", bugDAO.getAllBugsFromCompany(companyName));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("close")){  //Close Single
            int bugID = Integer.parseInt(request.getParameter("bugID"));
            bugDAO.closeBug(companyName, bugID);
            forward = CLOSE_BUG;
            request.setAttribute("bugs", bugDAO.getAllBugsFromCompany(companyName));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("edit")){  //edit Single
            int bugID = Integer.parseInt(request.getParameter("bugID"));
            BugBean bug = bugDAO.getBugByID(companyName, bugID);
            forward = EDIT_BUG;
            request.setAttribute("bug", bug);
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("listBugs")){ //List All
            forward = LIST_BUGS;
            request.setAttribute("bugs", bugDAO.getAllBugsFromCompany(companyName));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("listOpenBugs")){ //List Open Bugs Only
            forward = LIST_OPEN_BUGS;
            request.setAttribute("bugs", bugDAO.getAllOpenBugsFromCompany(companyName));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("listBugsForProject")){ //List All For Project
            forward = LIST_BUGS_FOR_PROJECT;
            int projectID = 0;
            if (request.getParameter("projectID") != null ) {
                projectID = Integer.parseInt(request.getParameter("projectID"));
                Cookie projectCookie = new Cookie("projectID" , request.getParameter("projectID"));
                projectCookie.setMaxAge(60*60*24); //1day cookie
                response.addCookie(projectCookie);
            } else {
                cookies = request.getCookies();
                for (int i = 0; i < cookies.length; i++){
                    Cookie cookie = cookies[i];
                    if(cookie.getName().equals("projectID")){ projectID = Integer.parseInt(cookie.getValue()); }
                }
            }
            System.out.print("projectID(Servlet): " + projectID);
            request.setAttribute("bugs", bugDAO.getAllBugsForProject(companyName, projectID));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("listOpenBugsForProject")){ //List Open Bugs Only For Project
            forward = LIST_OPEN_BUGS_FOR_PROJECT;
            int projectID = 0;
            if (request.getParameter("projectID") != null ) {
                projectID = Integer.parseInt(request.getParameter("projectID"));
                Cookie projectCookie = new Cookie("projectID" , request.getParameter("projectID"));
                projectCookie.setMaxAge(60*60*24); //1day cookie
                response.addCookie(projectCookie);
            } else {
                cookies = request.getCookies();
                for (int i = 0; i < cookies.length; i++){
                    Cookie cookie = cookies[i];
                    if(cookie.getName().equals("projectID")){ projectID = Integer.parseInt(cookie.getValue()); }
                }
            }
            request.setAttribute("bugs", bugDAO.getAllOpenBugsForProject(companyName, projectID));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("assignBugs")){ //Assign users to bugs
            forward = ASSIGN_BUGS;
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
                out.write("<option value='"+ projects.get(i).getProjectID() +"'>"+ projects.get(i).getProjectName() +" v. " +projects.get(i).getProjectID()+"</option>");
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

        } else if (action.equalsIgnoreCase("changeBugStatus")){ //change bug status
            response.setContentType("text/html");
            String bugStatus = request.getParameter("bugStatus");
            int bugID = Integer.parseInt(request.getParameter("bugID"));
            bugDAO.changeBugStatus(companyName,bugStatus, bugID);

        } else if (action.equalsIgnoreCase("changeBugPriority")){ //change bug priority
            response.setContentType("text/html");
            String bugPriority = request.getParameter("bugPriority");
            int bugID = Integer.parseInt(request.getParameter("bugID"));
            bugDAO.changeBugPriority(companyName,bugPriority, bugID);

        } else if (action.equalsIgnoreCase("setActive")){ //change bug active/inactive
            response.setContentType("text/html");
            boolean active;
            if (request.getParameter("active").equals("true")) {
                active = true;
            } else {
                active = false;
            }
            int bugID = Integer.parseInt(request.getParameter("bugID"));
            bugDAO.setActive(companyName,active, bugID);

        } else if (action.equalsIgnoreCase("setAssigned")){ //set users assigned to specific bug

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

        }else if (action.equalsIgnoreCase("autocompleteUsers")){
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String searchFor = request.getParameter("searchFor");
            List<UserBean> users= bugDAO.getUsersAutocomplete(searchFor,companyName) ;
            for(int i=0; i<users.size(); i++){
                out.print("<li data-user-id='"+ users.get(i).getUserid() +"'>"+ users.get(i).getFirstname()+" "+ users.get(i).getLastname() +"</li>");
            }

            } else {
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BugBean bug = new BugBean();

        //put Steps to recreate error into one string
        String stepsToRecreate = "";
        for(int i=1;i<10;i++){
            String param = request.getParameter("stepContent" + i);
            if(param != null && param != ""){
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
        if (request.getParameter("textFrom") != "" && request.getParameter("textTo") != ""){
            bug.setTextFromTo(request.getParameter("textFrom")+ "~" + request.getParameter("textTo"));
        }
        bug.setStepsToRecreate(stepsToRecreate);
        bug.setUserID( Integer.parseInt(request.getParameter("userID")));

        //get company name from cookie
        Cookie[] cookies ;
        String companyName = null;
        cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("cid")){ companyName = cookie.getValue(); }
        }

        bugDAO.addBug(companyName, bug);
        response.sendRedirect(INSERT_BUG);
    }

}
