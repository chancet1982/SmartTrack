package Beans;

public class UserBean {
    private int userid;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String usercompany) {
        this.companyName = usercompany;
    }

    private String firstname;
    private String lastname;
    private String useremail;
    private String userpassword;
    private String companyName;
    private boolean ishandler;
    private boolean ismanager;
    private boolean isreporter;

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    private String initials;

}