package Beans;

import java.io.Serializable;

public class CompanyBean implements Serializable {
    private String companyName;
    private String companyID;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String username) {
        this.companyName = username;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String username) {
        this.companyID = username;
    }
}
