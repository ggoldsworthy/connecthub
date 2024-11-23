package use_case.delete_post;

import daos.DBUserDataAccessObject;
import entity.Post;

/**
 * interactor for delete post
 */
public class DeletePostInteractor implements DeletePostInputBoundary {

    private final DeletePostDataAccessInterface postDataAccessObject;
    private final DeletePostOutputBoundary postPresenter;
    private final DBUserDataAccessObject userRepo;

    public DeletePostInteractor(DeletePostDataAccessInterface postDataAccessObject,
                                DeletePostOutputBoundary postPresenter,
                                DBUserDataAccessObject userRepo) {
        this.postDataAccessObject = postDataAccessObject;
        this.postPresenter = postPresenter;
        this.userRepo = userRepo;
    }

    @Override
    public void deletePost(DeletePostInputData deletePostInputData) {

        Post post = postDataAccessObject.postExistsById(deletePostInputData.getPostId());

        if (post == null) {
            postPresenter.prepareFailView("Post not found.");
            throw new DeletePostFailedException("The post with ID " + deletePostInputData.getPostId() + " does not exist.");
        }

        boolean userCanDelete = this.canDelete(deletePostInputData.getUserId(), post);

        if (!userCanDelete) {
            postPresenter.prepareFailView("User does not have permission to delete this post.");
            throw new DeletePostFailedException("User does not have permission to delete this post.");
        }

        boolean deletionSuccessful = postDataAccessObject.deletePost(deletePostInputData.getPostId());

        DeletePostOutputData outputData = new DeletePostOutputData(
                deletePostInputData.getPostId(),
                deletionSuccessful
        );

        if (deletionSuccessful) {
            postPresenter.prepareSuccessView(outputData);
        } else {
            postPresenter.prepareFailView("Failed to delete the Post.");
            throw new DeletePostFailedException("Failed to delete the Post.");
        }
    }

    private boolean canDelete(String userId, Post post) {
        if (userId.equals(post.getAuthor())) {
            return true;
        }

        return userRepo.getCurrentUser().getModerating().contains(post.getCategory());
    }

    @Override
    public void switchToDeletePostView() {
        postPresenter.switchToDeletePostView();
    }
}
