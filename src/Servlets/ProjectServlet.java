package Servlets;

import Beans.ProjectBean;
import Beans.UserBean;
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
import java.util.ArrayList;
import java.util.List;

public class ProjectServlet extends HttpServlet{

    ProjectDAO projectDAO;
    UserDAO userDAO;

    private static String LIST_PROJECTS = "/listProjects.jsp";
    private static String ASSIGN_PROJECTS = "/assignUsersToProjects.jsp";
    private static String INSERT_PROJECT = "/ProjectServlet?action=listProjects";
    public ProjectServlet(){
        super();
        projectDAO = new ProjectDAO();
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
            int projectId = Integer.parseInt(request.getParameter("projectID"));
            projectDAO.deleteProject(companyName, projectId);
            forward = LIST_PROJECTS;
            request.setAttribute("projects", projectDAO.getAllProjectsFromCompany(companyName));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("listProjects")){ //List All
            forward = LIST_PROJECTS;
            request.setAttribute("projects", projectDAO.getAllProjectsFromCompany(companyName));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("assignProjects")){ //Assign users to projects
            forward = ASSIGN_PROJECTS;
            userDAO = new UserDAO();
            request.setAttribute("projects", projectDAO.getAllProjectsFromCompany(companyName));
            request.setAttribute("users", userDAO.getAllUsersFromCompany(companyName));
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);

        } else if (action.equalsIgnoreCase("getAssigned")){ //get user assigned to specific project
            response.setContentType("text/html");
            int projectID = Integer.parseInt(request.getParameter("projectID"));
            List<UserBean> users = projectDAO.getUsersAssigned(companyName , projectID);
            PrintWriter out = response.getWriter();
            for(int i=0; i<users.size(); i++){
                out.write("<li class='user ico ui-draggable' data-user-id='"+users.get(i).getUserid()+"'>"+
                        "<span class='icon user'></span>" +
                         users.get(i).getFirstname().substring(0,1).toUpperCase() +
                         users.get(i).getLastname().substring(0,1).toUpperCase() +
                         "</li>");
            }

        }else if (action.equalsIgnoreCase("setAssigned")){ //set users assigned to specific project
            int projectID = Integer.parseInt(request.getParameter("projectID"));
            String[] assignedUsers = request.getParameter("assignedUsers").split(":");
            projectDAO.emptyProjectAssignment(companyName , projectID);
            System.out.println("user assigned currently ");
            try {
                if (assignedUsers.length >0) {
                    for(int i=1; i<assignedUsers.length; i++){
                        System.out.println(assignedUsers[i].toString());
                        projectDAO.assignUser(companyName , projectID , Integer.parseInt(assignedUsers[i]) );
                    }
                }
            } catch (NumberFormatException e) {
                System.out.print("SOMETHING IS NOT DEFINED");
            }
            PrintWriter out = response.getWriter();
        } else {
            forward = "";
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProjectBean project = new ProjectBean();
        project.setProjectName(request.getParameter("projectName"));
        project.setProjectVersion(request.getParameter("projectVersion"));
        project.setStartDate(request.getParameter("startDate"));
        project.setEndDate(request.getParameter("endDate"));

        //get company name from cookie
        Cookie[] cookies ;
        String companyName = null;
        cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("cid")){ companyName = cookie.getValue(); }
        }

        projectDAO.addProject( companyName , project );
        response.sendRedirect(INSERT_PROJECT);
    }
}
