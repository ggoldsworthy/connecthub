package use_case.delete_post;

import entity.Post;

/**
 * Interface for data access operations related to deleting a post.
 */
public interface DeletePostDataAccessInterface {

   boolean deletePost(String postID) throws DeletePostFailedException;

    /**
     * Retrieves a post by its ID.
     *
     * @param postId The ID of the post to retrieve.
     * @return The Post object if found, or null if not.
     */
    Post getPostByEntryId(String postId);

    boolean postExistsByID(String postId);
}

