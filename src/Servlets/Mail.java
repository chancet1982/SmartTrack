package Servlets;

// File Name SendEmail.java
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Mail extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String to = request.getParameter("to");
        String CompanyName = request.getParameter("CompanyName");
        //to = "chancet1982@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "web@gmail.com";
        String host = "localhost";

        // Get system properties and create mail server
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("You were just invited to join: " + CompanyName + "On SmartTrack a smarter bug tracking system");

            // Send the actual HTML message, as big as you like
            message.setContent("<a href='localhost://invitedUser.jsp?email="+to+"&companyName="+CompanyName+"'>Click Here to join!</a>",
                    "text/html" );

            // Send message
            Transport.send(message);
            String title = "Send Email";
            String res = "Sent message successfully....";
            String docType =
                    "<!doctype html public \"-//w3c//dtd html 4.0 " +
                            "transitional//en\">\n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<p align=\"center\">" + res + "</p>\n" +
                    "</body></html>");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}