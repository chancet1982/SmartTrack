package UserServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import UserDAO.*;
import UserBean.*;

public class CompanyServlet extends HttpServlet {
    private CompanyDAO dao;

    public CompanyServlet() {
        super();
        dao = new CompanyDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompanyBean user = new CompanyBean();
        PrintWriter out = response.getWriter();
        List<String> existingCompanies = dao.getAllCompanies();

        for (int i=0;i<existingCompanies.size();i++) {
            out.println("<option>" + existingCompanies.get(i) + "</option>");
            System.out.println("option add"+ existingCompanies);
        }
    }

}
