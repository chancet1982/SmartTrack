package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAOs.*;
import Beans.*;

public class CompanyServlet extends HttpServlet {
    private CompanyDAO dao;

    public CompanyServlet() {
        super();
        dao = new CompanyDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in the companyServlet");
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("verifyUnique")){

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            if( dao.isFieldUnique(request.getParameter("inputValue") , request.getParameter("inputName")) == true ){
                String temp = "{\"isUnique\":\"true\"}";
                out.write(temp);
            }else{
                String temp = "{\"isUnique\":\"false\"}";
                out.write(temp);
            }
        }
    }
}
