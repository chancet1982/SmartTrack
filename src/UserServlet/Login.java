package UserServlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import UserDAO.*;
import UserBean.*;
import UserDB.*;
import Utilities.PasswordHash;

import static Utilities.PasswordHash.createHashEmail;

public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/index.html";
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
        } else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");
        String hashedDBPassword = "" ;
        HttpSession session = request.getSession();

        //Fetch password from DB
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usersTable WHERE userEmail=?");
            preparedStatement.setString(1,email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {hashedDBPassword = rs.getString("userPassword");}
        }
        catch (SQLException e) {e.printStackTrace();}

        //Validate password
        try {
            if( passwordHash.validatePassword(password, hashedDBPassword) ){
                System.out.println("Success - password correct");
                Cookie emailCookie = new Cookie("uid" , createHashEmail(email) );
                Cookie pwdCookie = new Cookie( "pwd" , hashedDBPassword );

                emailCookie.setMaxAge(60*60*24); //1day cookie
                pwdCookie.setMaxAge(60*60*24);
                response.addCookie(emailCookie);
                response.addCookie(pwdCookie);
                RequestDispatcher view = request.getRequestDispatcher("afterLogin.jsp");
                view.forward(request, response);

            }else{
                System.out.println("Fail - password incorrect");
                RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                view.forward(request, response);
            }
        }
        catch (NoSuchAlgorithmException e) {e.printStackTrace();}
        catch (InvalidKeySpecException e) {e.printStackTrace();}

    }
}
