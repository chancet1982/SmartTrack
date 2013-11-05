package UserBean;

import java.util.Date;

public class UserBean {

    private int userid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getUsersalt() {
        return usersalt;
    }

    public void setUsersalt(String usersalt) {
        this.usersalt = usersalt;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public boolean isIshandler() {
        return ishandler;
    }

    public void setIshandler(boolean ishandler) {
        this.ishandler = ishandler;
    }

    public boolean isIsmanager() {
        return ismanager;
    }

    public void setIsmanager(boolean ismanager) {
        this.ismanager = ismanager;
    }

    public boolean isIsreporter() {
        return isreporter;
    }

    public void setIsreporter(boolean isreporter) {
        this.isreporter = isreporter;
    }

    private String username;
    private String firstname;
    private String lastname;
    private String useremail;
    private String usersalt;
    private String userpassword;
    private boolean ishandler;
    private boolean ismanager;
    private boolean isreporter;

}