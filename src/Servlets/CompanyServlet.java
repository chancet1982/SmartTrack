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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompanyBean user = new CompanyBean();
        PrintWriter out = response.getWriter();
        List<String> existingCompaniesNames = dao.getAllCompaniesNames();
        List<String> existingCompaniesIDs = dao.getAllCompaniesIDs();

        for (int i=0;i<existingCompaniesNames.size();i++) {
            out.println("<option value="+ existingCompaniesIDs.get(i) + ":" + existingCompaniesNames.get(i) +">" + existingCompaniesNames.get(i) + "</option>");
        }
    }

}
