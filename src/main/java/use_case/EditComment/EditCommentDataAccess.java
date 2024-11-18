package use_case.EditComment;

public interface EditCommentDataAccessInterface {

    /**
     * Updates a comment in the data source using the provided Post object.
     *
     * @param post the Post object containing updated comment details
     * @throws EditCommentFailed if the comment could not be updated
     */
    void editComment(Post post) throws EditCommentFailed;
}