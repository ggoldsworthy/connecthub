package use_case.create_post;


public interface CreatePostInputBoundary {

    /**
     * Executes the createPost use case.
     * @param createPostInputData the input data
     */
    void createPost(CreatePostInputData createPostInputData);
}