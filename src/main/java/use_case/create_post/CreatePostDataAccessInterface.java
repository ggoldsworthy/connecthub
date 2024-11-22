package use_case.create_post;

import entity.Post;

public interface CreatePostDataAccessInterface {

    /**
     * Creates a post.
     *
     * @param post The post to be created.
     */
    void createPost(Post post);
}

