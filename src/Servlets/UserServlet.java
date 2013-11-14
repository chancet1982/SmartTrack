package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.*;
import Beans.*;

public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/assignUserRoles.jsp";
    private UserDAO dao;
    private CompanyDAO companydao;

    public UserServlet() {
        super();
        dao = new UserDAO();
        companydao = new CompanyDAO();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            dao.deleteUser(userId);
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            UserBean user = dao.getUserById(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUsers")){
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());
        } else if (action.equalsIgnoreCase("verifyUnique")){

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            if( dao.emailExists(request.getParameter("email"))== true ){
                System.out.println("{\"isUnique\":\"true\"}");
                String temp = "{\"isUnique\":\"true\"}";
                out.write(temp);
            }else{
                System.out.println("{\"isUnique\":\"false\"}");
                String temp = "{\"isUnique\":\"false\"}";
                out.write(temp);
            }

        }else{
            forward = INSERT_OR_EDIT;
        }

        //RequestDispatcher view = request.getRequestDispatcher(forward);
        //view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean user = new UserBean();
        CompanyBean company = new CompanyBean();

        user.setFirstname(request.getParameter("firstName"));
        user.setLastname(request.getParameter("lastName"));
        user.setUseremail(request.getParameter("userEmail"));
        user.setUserpassword(request.getParameter("userPassword"));

//        // Split the info into parameters
//        String[] companyInfo = (request.getParameter("companyInfo")).split(":");
//        if(companyInfo.length>1){
//            user.setCompanyID(request.getParameter(companyInfo[0]));
//            company.setCompanyName(request.getParameter(companyInfo[1]));
//        }else{
//            company.setCompanyName(request.getParameter(companyInfo[1]));
//            user.setCompanyID(user.getCompanyID());
//        }
        System.out.println("in the servlet:" + request.getParameter("userPassword"));
        user.setIshandler(true);
        user.setIsmanager(true);
        user.setIsreporter(true);

        String userid = request.getParameter("userID");
        if(userid == null || userid.isEmpty()) {
            dao.addUser(user);
            companydao.addCompany(company);
        } else {
            user.setUserid(Integer.parseInt(userid));
            dao.updateUser(user);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        //request.setAttribute("users", dao.getAllUsers());
        view.forward(request, response);
    }
}