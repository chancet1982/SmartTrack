package Servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import Beans.UserBean;
import DAOs.*;
import DB.*;
import Utilities.PasswordHash;

import static Utilities.PasswordHash.createHashEmail;

public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO dao;
    PasswordHash passwordHash = new PasswordHash();

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public Login() {
        super();
        dao = new UserDAO();
        connection = DB.getConnection();
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
                statement = connection.createStatement();
                rs = statement.executeQuery("SHOW DATABASES LIKE 'indexdb'");
                if (rs.next() ) {
                    rs.close();
                    preparedStatement = connection.prepareStatement("SELECT * FROM indexdb.usersTable WHERE userEmail=?");
                    preparedStatement.setString(1,email);
                    rs = preparedStatement.executeQuery();
                    if (rs.next()) {hashedDBPassword = rs.getString("userPassword");} else {
                        System.out.println("username cannot be found");
                    }
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
        UserDAO userDAO = new UserDAO();
        UserBean user = userDAO.getUserByEmail(email);


        //Validate password
        try {
            if ( password!=null && user.getUserpassword() != null ) {
                if( passwordHash.validatePassword(password, user.getUserpassword()) ){
                    System.out.println("Success - password correct");
                    Cookie emailCookie = new Cookie("uid" , email);
                    Cookie pwdCookie = new Cookie( "pwd" , user.getUserpassword() );
                    Cookie cid = new Cookie("cid", user.getCompanyName());

                    emailCookie.setMaxAge(60*60*24); //1day cookie
                    pwdCookie.setMaxAge(60*60*24);
                    cid.setMaxAge(60*60*24);
                    response.addCookie(emailCookie);
                    response.addCookie(pwdCookie);
                    response.addCookie(cid);
                    response.sendRedirect("afterLogin.jsp");

                }else{
                    System.out.println("Fail - password incorrect "+ user.getUserpassword());
                    response.sendRedirect("login.jsp?message='Passwords Mismatch'");
                }
            } else {
                response.sendRedirect("login.jsp?message='No Password'");
            }

        }
        catch (NoSuchAlgorithmException e) {e.printStackTrace();}
        catch (InvalidKeySpecException e) {e.printStackTrace();}
    }
}