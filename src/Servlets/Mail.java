package Servlets;

// File Name SendEmail.java
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //final String username = "chancet1982@gmail.com";
        //final String password = "SMRI19820713";

        String from = "info@smarttrack.com";
        String to = request.getParameter("to");
        String CompanyName = request.getParameter("CompanyName");
        //String to = "chancet1982@gmail.com";
        Properties props = new Properties();

        InputStream inputStream = Mail.class.getClassLoader().getResourceAsStream("/mail.properties");
        props.load(inputStream);

        final String username = props.getProperty("username");
        final String password = props.getProperty("password");

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Smarttrack Invitation");
            message.setContent("<h1>You have been invited to use Smarttrack</h1><p>click the link below to join</p><a HREF=''>Click to join</a>" , "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Done");
            response.sendRedirect("afterLogin.jsp?message=Success");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}