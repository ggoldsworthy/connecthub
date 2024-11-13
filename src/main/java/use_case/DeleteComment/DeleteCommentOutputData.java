package use_case.DeleteComment;

/**
 * This class contains the output data for the deletion of a comment.
 *
 */
public class DeleteCommentOutputData {

    private final String commentId;
    private final boolean deletionFailed;

    /**
     * Constructs a DeleteCommentOutputData object with the specified comment ID
     * and the result of the deletion attempt.
     *
     * @param commentId The ID of the comment being deleted
     * @param deletionFailed A boolean indicating if the deletion operation failed
     */
    public DeleteCommentOutputData(String commentId, boolean deletionFailed) {
        this.commentId = commentId;
        this.deletionFailed = deletionFailed;
    }

    /**
     * Returns whether the deletion of the comment failed.
     *
     * @return true if the deletion failed, false if it was successful
     */
    public boolean isDeletionFailed() {
        return deletionFailed;
    }

    /**
     * Returns the ID of the comment that was attempted to be deleted.
     *
     * @return the ID of the comment
     */
    public String getCommentId() {
        return commentId;
    }
}
