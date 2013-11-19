package Servlets;

import Beans.ProjectBean;
import DAOs.ProjectDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProjectServlet extends HttpServlet{
    ProjectDAO projectDAO;
    private static String LIST_PROJECTS = "/listProjects.jsp";
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
