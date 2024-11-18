package use_case.EditComment;

public class EditCommentInteractor implements EditCommentInputBoundary {

    private final EditCommentDataAccessInterface editCommentDB;
    private final EditCommentOutputBoundary editCommentOutput;

    /**
     * Constructor for EditCommentInteractor.
     *
     * @param editCommentDB       the data access interface for editing comment data
     * @param editCommentOutput   the output boundary for handling the result of the operation
     */
    public EditCommentInteractor(EditCommentDataAccessInterface editCommentDB, EditCommentOutputBoundary editCommentOutput) {
        this.editCommentDB = editCommentDB;
        this.editCommentOutput = editCommentOutput;
    }

    @Override
    public void editComment(EditCommentInputData inputData) {
        try {
            if (inputData.getNewContent() == null || inputData.getNewContent().isEmpty()) {
                throw new EditCommentFailed("New comment content cannot be empty.");
            }

            // Access database to update the comment
            boolean success = editCommentDB.updateComment(inputData.getCommentId(), inputData.getNewContent());

            if (success) {
                editCommentOutput.presentSuccess("Comment updated successfully.");
            } else {
                throw new EditCommentFailed("Failed to update comment in the database.");
            }

        } catch (EditCommentFailed e) {
            editCommentOutput.presentError(e.getMessage());
        }
    }

    @Override
    public boolean canEdit(User user) {
        // Example logic: Check if the user is an admin or editor
        return "admin".equals(user.getRole()) || "editor".equals(user.getRole());
    }
}