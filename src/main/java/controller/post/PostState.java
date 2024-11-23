package controller.post;

import java.util.ArrayList;
import java.util.List;
import entity.Comment;

public class PostState {
    private String postContent = "";
    private String fullNameError;
    private List<Comment> comments = new ArrayList<>();
    private List<Comment> commentsError;
    
    public String getPostContent() {
        return postContent;
    }
    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
    public String getFullNameError() {
        return fullNameError;
    }
    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    public List<Comment> getCommentsError() {
        return commentsError;
    }
    public void setCommentsError(List<Comment> commentsError) {
        this.commentsError = commentsError;
    }

    @Override
    public String toString() {
        return "PostState{" 
            + "post_content='" + postContent + '\''
            + "}";
    }
}
