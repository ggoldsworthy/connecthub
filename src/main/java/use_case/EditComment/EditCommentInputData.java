package use_case.EditComment;

public class EditCommentInputData {
    private String content;
    private String commentId;

    // Constructor to initialize all parameters
    public EditCommentInputData(String content, String commentId) {
        this.content = content;
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

    // Getter for commentId
    public String getCommentId() {
        return commentId;
    }

    // Setter for commentId
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    // Method to update all parameters
    public void updatePostInputData(String content, String commentId) {
        this.content = content;
        this.commentId = commentId;
    }
}