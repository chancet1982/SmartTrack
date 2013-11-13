package Servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import DAOs.*;
import DB.*;
import Utilities.PasswordHash;

import static Utilities.PasswordHash.createHashEmail;

public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO dao;
    PasswordHash passwordHash = new PasswordHash();

    private Connection connection;
    public Login() {
        super();
        dao = new UserDAO();
        connection = UserDB.getConnection();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("validatecookie")){
            System.out.println("Im in the servlet");
            String password = null, email = null;
            Cookie[] cookies;

            cookies = request.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("uid")) {
                    System.out.println("UID cookie to variable");
                    email = cookies[i].getValue();
                }
                if (cookies[i].getName().equals("pwd")) {
                    System.out.println("PWD cookie to variable");
                    password = cookies[i].getValue();
                }
            }

            String hashedDBPassword = "" ;

            //Fetch password from DB
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM indexdb.usersTable WHERE userEmail=?");
                preparedStatement.setString(1,email);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {hashedDBPassword = rs.getString("userPassword");} else {
                    System.out.println("username cannot be found");
                }
            }
            catch (SQLException e) {e.printStackTrace();}
            System.out.println("cookie password: " + password );
            System.out.println("hashed password: " + hashedDBPassword);
            if (hashedDBPassword.equals(password)) {
                System.out.println("cookie password validation pass!");
                response.sendRedirect("afterLogin.jsp");
            } else {
                System.out.println("cookie password validation FAIL!");
                response.sendRedirect("login.jsp");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");
        String hashedDBPassword = "" ;
        HttpSession session = request.getSession();

        //Fetch password from DB
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM indexdb.usersTable WHERE userEmail=?");
            preparedStatement.setString(1,email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {hashedDBPassword = rs.getString("userPassword");}
        }
        catch (SQLException e) {e.printStackTrace();}

        //Validate password
        try {
            if( passwordHash.validatePassword(password, hashedDBPassword) ){
                System.out.println("Success - password correct");
                Cookie emailCookie = new Cookie("uid" , email);
                Cookie pwdCookie = new Cookie( "pwd" , hashedDBPassword );

                emailCookie.setMaxAge(60*60*24); //1day cookie
                pwdCookie.setMaxAge(60*60*24);
                response.addCookie(emailCookie);
                response.addCookie(pwdCookie);
                //RequestDispatcher view = request.getRequestDispatcher("afterLogin.jsp");
                //view.forward(request, response);
                response.sendRedirect("afterLogin.jsp");

            }else{
                System.out.println("Fail - password incorrect");
                response.sendRedirect("afterLogin.jsp?message='error'");
                //RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                //view.forward(request, response);
            }
        }
        catch (NoSuchAlgorithmException e) {e.printStackTrace();}
        catch (InvalidKeySpecException e) {e.printStackTrace();}

    }
}