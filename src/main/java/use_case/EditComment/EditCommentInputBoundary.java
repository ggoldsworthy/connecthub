package use_case.EditComment;

// Interface: EditCommentInputBoundary.java
public interface EditCommentInputBoundary {

    /**
     * Edits a comment based on the provided input data.
     *
     * @param inputData the data required to edit the comment
     */
    void editComment(EditCommentInputData inputData);

    /**
     * Checks if the given user has the permissions to edit a comment.
     *
     * @param user the user whose permissions are being checked
     * @return true if the user can edit the comment, false otherwise
     */
    boolean canEdit(User user);
}