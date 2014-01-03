package Beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class BugBean implements Serializable {

    private int bugID;
    private int projectID;
    private String bugCategory;
    private String bugTitle;
    private String bugDescription;

    private String bugStatus;
    private String bugPriority;
    private String reportedPriority;

    private String bugURL;
    private String screenshotURL;
    private String bugPCInfo;

    private String bugErrorCode;
    private String textFromTo;
    private String bugTimeStamp;

    private String stepsToRecreate;
    private String created;
    private String modified;

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getStepsToRecreate() {
        return stepsToRecreate;
    }

    public void setStepsToRecreate(String stepsToRecreate) {
        this.stepsToRecreate = stepsToRecreate;
    }

    public String getBugTimeStamp() {
        return bugTimeStamp;
    }

    public void setBugTimeStamp(String bugTimeStamp) {
        this.bugTimeStamp = bugTimeStamp;
    }

    public int getBugID() {
        return bugID;
    }

    public void setBugID(int bugID) {
        this.bugID = bugID;
    }

    public String getBugCategory() {
        return bugCategory;
    }

    public void setBugCategory(String bugCategory) {
        this.bugCategory = bugCategory;
    }

    public String getBugTitle() {
        return bugTitle;
    }

    public void setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle;
    }

    public String getBugDescription() {
        return bugDescription;
    }

    public void setBugDescription(String bugDescription) {
        this.bugDescription = bugDescription;
    }

    public String getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(String bugStatus) {
        this.bugStatus = bugStatus;
    }

    public String getBugPriority() {
        return bugPriority;
    }

    public void setBugPriority(String bugPriority) {
        this.bugPriority = bugPriority;
    }

    public String getReportedPriority() {
        return reportedPriority;
    }

    public void setReportedPriority(String reportedPriority) {
        this.reportedPriority = reportedPriority;
    }

    public String getBugURL() {
        return bugURL;
    }

    public void setBugURL(String bugURL) {
        this.bugURL = bugURL;
    }

    public String getScreenshotURL() {
        return screenshotURL;
    }

    public void setScreenshotURL(String screenshotURL) {
        this.screenshotURL = screenshotURL;
    }

    public String getBugPCInfo() {
        return bugPCInfo;
    }

    public void setBugPCInfo(String bugPCInfo) {
        this.bugPCInfo = bugPCInfo;
    }

    public String getBugErrorCode() {
        return bugErrorCode;
    }

    public void setBugErrorCode(String bugErrorCode) {
        this.bugErrorCode = bugErrorCode;
    }

    public String getTextFromTo() {
        return textFromTo;
    }

    public void setTextFromTo(String textFromTo) {
        this.textFromTo = textFromTo;
    }



}
