package Beans;

import java.io.Serializable;

public class UserBean implements Serializable{
    private int userID;
    private String firstname;
    private String lastname;
    private String useremail;
    private String userpassword;
    private String companyName;
    private String initials;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isHandler() {
        return handler;
    }

    public void setHandler(boolean handler) {
        this.handler = handler;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public boolean isReporter() {
        return reporter;
    }

    public void setReporter(boolean reporter) {
        this.reporter = reporter;
    }

    private boolean handler;
    private boolean manager;
    private boolean reporter;

    public int getUserid() {
        return userID;
    }

    public void setUserid(int userid) {
        this.userID = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String usercompany) {
        this.companyName = usercompany;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }



}