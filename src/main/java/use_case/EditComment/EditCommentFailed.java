package use_case.EditComment;

public class EditCommentFailed extends Exception {

    /**
     * Constructs a new EditCommentFailed exception with the specified error message.
     *
     * @param error the error message describing the failure
     */
    public EditCommentFailed(String error) {
        super(error);
    }
}
