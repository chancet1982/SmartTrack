package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utilities.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import DAOs.*;
import Beans.*;

public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String EDIT = "/editUser.jsp";
    private static String LIST_USER = "/assignUserRoles.jsp";
    private static String INSERT = "/afterLogin.jsp";
    private UserDAO dao;
    private CompanyDAO companydao;
    PasswordHash passwordHash = new PasswordHash();

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
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } else if (action.equalsIgnoreCase("edit")){
            forward = EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            UserBean user = dao.getUserById(userId);
            request.setAttribute("user", user);
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } else if (action.equalsIgnoreCase("listUsers")){
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } else if (action.equalsIgnoreCase("verifyUnique")){

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            if( dao.isFieldUnique(request.getParameter("inputValue") , request.getParameter("inputName")) == true ){
                String temp = "{\"isUnique\":\"true\"}";
                out.write(temp);
            }else{
                String temp = "{\"isUnique\":\"false\"}";
                out.write(temp);
            }

        }else{
            forward = EDIT;
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserBean user = new UserBean();
        CompanyBean company = new CompanyBean();
        String hashedPassword = null;
        user.setFirstname(request.getParameter("firstName"));
        user.setLastname(request.getParameter("lastName"));
        user.setUseremail(request.getParameter("userEmail"));

        try {
            hashedPassword = passwordHash.createHash(request.getParameter("userPassword"));
        } catch ( InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        user.setUserpassword(hashedPassword);
        user.setCompanyName(request.getParameter("companyName"));
        company.setCompanyName(request.getParameter("companyName"));

        user.setIshandler(true);
        user.setIsmanager(true);
        user.setIsreporter(true);

        String userid = request.getParameter("userID");
        if(userid == null || userid.isEmpty()) {
            System.out.print("Creating new user");

            //Adding email and Company Cookie
            Cookie emailCookie = new Cookie("uid" , request.getParameter("userEmail"));
            Cookie companyNameCookie = new Cookie("cid" , request.getParameter("companyName"));
            Cookie passwordCookie = new Cookie("pwd" , hashedPassword);
            emailCookie.setMaxAge(60*60*24); //1day cookie
            companyNameCookie.setMaxAge(60*60*24);
            passwordCookie.setMaxAge(60*60*24);
            response.addCookie(emailCookie);
            response.addCookie(companyNameCookie);
            response.addCookie(passwordCookie);

            response.sendRedirect("afterLogin.jsp");
            dao.addUser(user);
            companydao.addCompany(company);
        } else {
            System.out.print("Trying to update user");
            user.setUserid(Integer.parseInt(userid));
            dao.updateUser(user);
            RequestDispatcher view = request.getRequestDispatcher(INSERT);
            view.forward(request, response);
        }
    }
}