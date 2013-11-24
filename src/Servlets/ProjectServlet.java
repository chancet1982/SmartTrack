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
            int projectId = Integer.parseInt(request.getParameter("projectId"));
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
                         users.get(i).getFirstname() + "</li>");
            }

        }else if (action.equalsIgnoreCase("setAssigned")){ //set users assigned to specific project

            int projectID = Integer.parseInt(request.getParameter("projectID"));
            String[] assignedUsers = request.getParameter("assignedUsers").split(":");

            projectDAO.emptyProjectAssignment(companyName , projectID);
            for(int i=1; i<assignedUsers.length; i++){
                projectDAO.assignUser(companyName , projectID , Integer.parseInt(assignedUsers[i]) );
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

        //get company name from cookie
        Cookie[] cookies ;
        String companyName = null;
        cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("cid")){ companyName = cookie.getValue(); }
        }
        System.out.println("project has been added");
        projectDAO.addProject( companyName , project );
        response.sendRedirect("afterLogin.jsp");
    }
}
