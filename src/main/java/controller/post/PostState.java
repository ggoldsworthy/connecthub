package controller.post;

import java.util.ArrayList;
import java.util.List;
import entity.Comment;

public class PostState {
    private String postTitle = "";
    private String postTitleError;
    private String postContent = "";
    private String postContentError;
    private List<Comment> comments = new ArrayList<>();
    private List<Comment> commentsError;
    
    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostTitleError() {
        return postTitleError;
    }

    public void setPostTitleError(String postTitleError) {
        this.postTitleError = postTitleError;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPostContentError() {
        return postContentError;
    }

    public void setPostContentError(String postContentError) {
        this.postContentError = postContentError;
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
