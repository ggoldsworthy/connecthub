package use_case.DeleteComment;

import entity.Comment;

/**
 * Interactor responsible for deleting a comment
 */
public class DeleteCommentInteractor implements DeleteCommentInputBoundary {

    private final DeleteCommentDataAccessInterface commentDataAccessObject;
    private final DeleteCommentOutputBoundary commentPresenter;

    public DeleteCommentInteractor(DeleteCommentDataAccessInterface commentDataAccessObject,
                                   DeleteCommentOutputBoundary commentPresenter) {
        this.commentDataAccessObject = commentDataAccessObject;
        this.commentPresenter = commentPresenter;
    }

    @Override
    public void execute(DeleteCommentInputData deleteCommentInputData) {
        if (deleteCommentInputData.getCommentId() == null ) {
            commentPresenter.prepareFailView("commentId cannot be empty.");
            return;
        }

        boolean deletionFailed = false;
        Comment comment = commentDataAccessObject.existsCommentById(deleteCommentInputData.getCommentId());

        if (comment == null) {
            deletionFailed = true;
            commentPresenter.prepareFailView("Comment not found.");
        } else {
            commentDataAccessObject.deleteComment(deleteCommentInputData.getCommentId());

            DeleteCommentOutputData outputData = new DeleteCommentOutputData(
                    deleteCommentInputData.getCommentId(),
                    deletionFailed
            );

            if (deletionFailed) {
                commentPresenter.prepareFailView("Failed to delete the comment.");
            } else {
                commentPresenter.prepareSuccessView(outputData);
            }
        }
    }

    /**
     * Switches to the Delete Comment View.
     */
    public void switchToDeleteCommentView() {
        commentPresenter.switchToDeleteCommentView();
    }
}

