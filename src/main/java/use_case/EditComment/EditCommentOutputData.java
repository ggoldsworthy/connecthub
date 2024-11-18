package use_case.EditComment;

public class EditCommentOutputData {
    private String commentId;
    private String content;
    private boolean updateSuccessful;

    // Constructor to initialize all parameters
    public EditCommentOutputData(String commentId, String content, boolean updateSuccessful) {
        this.commentId = commentId;
        this.content = content;
        this.updateSuccessful = updateSuccessful;
    }

    // Getter for commentId
    public String getCommentId() {
        return commentId;
    }

    // Setter for commentId
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    // Getter for content
    public String getContent() {
        return content;
    }

    // Setter for content
    public void setContent(String content) {
        this.content = content;
    }

    // Getter for updateSuccessful
    public boolean isUpdateSuccessful() {
        return updateSuccessful;
    }

    // Setter for updateSuccessful
    public void setUpdateSuccessful(boolean updateSuccessful) {
        this.updateSuccessful = updateSuccessful;
    }

    // Method to update all parameters
    public void updatePostOutputData(String commentId, String content, boolean updateSuccessful) {
        this.commentId = commentId;
        this.content = content;
        this.updateSuccessful = updateSuccessful;
    }
}