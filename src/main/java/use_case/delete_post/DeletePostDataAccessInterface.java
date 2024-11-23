package use_case.delete_post;

import entity.Post;

/**
 * Interface for data access operations related to deleting a post
 */
public interface DeletePostDataAccessInterface {

    boolean deletePost(String postID) throws DeletePostFailedException;

    /**
     * Finds a post by its ID
     *
     * @param postId The ID of the ost to find.
     * @return The ost if found, or null if not.
     */
    Post postExistsById(String postId);
}


