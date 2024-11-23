package use_case.delete_post;

import daos.DBUserDataAccessObject;
import entity.Post;
import entity.User;

/**
 * Interactor for delete post.
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
    public boolean deletePost(DeletePostInputData deletePostInputData) {
        User currentUser = userRepo.getCurrentUser();

        if (!currentUser.getPosts().contains(deletePostInputData.getPostId())) {
            postPresenter.prepareFailView("Post not found in user's posts.");
            throw new DeletePostFailedException("The post with ID " + deletePostInputData.getPostId() + " does not exist in the user's posts.");
        }

        Post post = postDataAccessObject.getPostByEntryId(deletePostInputData.getPostId());
        if (post == null) {
            postPresenter.prepareFailView("Failed to retrieve post details.");
            throw new DeletePostFailedException("The post details could not be retrieved.");
        }

        boolean userCanDelete = canDelete(currentUser.getUserID(), post);
        if (!userCanDelete) {
            postPresenter.prepareFailView("User does not have permission to delete this post.");
            throw new DeletePostFailedException("User does not have permission to delete this post.");
        }

        try {
            postDataAccessObject.deletePost(deletePostInputData.getPostId());

            DeletePostOutputData outputData = new DeletePostOutputData(
                    deletePostInputData.getPostId(),
                    true
            );
            postPresenter.prepareSuccessView(outputData);
            return true;
        } catch (Exception e) {
            DeletePostOutputData outputData = new DeletePostOutputData(
                    deletePostInputData.getPostId(),
                    false
            );
            postPresenter.prepareFailView("Failed to delete the post.");
            throw new DeletePostFailedException("Failed to delete the post.");
        }
    }

    private boolean canDelete(String userId, Post post) {
        // Check if the user is the author of the post or a moderator of the category
        return userId.equals(post.getAuthor()) ||
                userRepo.getCurrentUser().getModerating().contains(post.getCategory());
    }


    // TODO delete the post from the user's posts

    // TODO we will need to switch to homeview or something that will be done
    //  later. WHEN THIS IS DONE TAKE AWAY THE ABSTRACT DECLARATION ON LINE 9
    @Override
    public void switchToDeletePostView() {
        // Implementation to be added later
    }
}
