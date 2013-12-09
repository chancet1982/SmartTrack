package Beans;

public class CommentBean {
    private int commentID;
    private String commentContent;
    private int commentUserID;
    private int commentBugID;
    private String created;
    private String modified;

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

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getCommentUserID() {
        return commentUserID;
    }

    public void setCommentUserID(int commentUserID) {
        this.commentUserID = commentUserID;
    }

    public int getCommentBugID() {
        return commentBugID;
    }

    public void setCommentBugID(int commentBugID) {
        this.commentBugID = commentBugID;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

}
