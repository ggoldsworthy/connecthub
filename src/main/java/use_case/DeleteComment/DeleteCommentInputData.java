package use_case.DeleteComment;

/**
Input Data for the Delete Comment use-case .
 */
public class DeleteCommentInputData {
    // THIS WILL HAVE TO BE FIXED LATER WHEN WE HAVE THE DATABASE SET UP
    private String commentId;

    public DeleteCommentInputData(String commentId) {
    this.commentId = commentId;
    }
    String getCommentId() {
        return commentId;
    }

}
