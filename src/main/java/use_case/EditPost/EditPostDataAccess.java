package use_case.EditPost;

public interface EditPostDataAccess {

    // Method to edit a post
    void editPost(Post post) throws EditPostFailed;
}
