package controller.createPost;

import entity.Comment;
import entity.PostContent;
import entity.User;
import use_case.createPost.CreatePostInputBoundary;
import use_case.createPost.CreatePostInputData;


import java.time.LocalDateTime;
import java.util.List;

public class createPostController {

    private final CreatePostInputBoundary createPostInputBoundary;

    public createPostController(CreatePostInputBoundary createPostInteractor) {
        this.createPostInputBoundary = createPostInteractor;
    }

    public void execute(String entryID, User author, PostContent content, LocalDateTime timestamp,
                        LocalDateTime lastModified, int dislikes, int likes, String postTitle,
                        List<User> moderators, List<Comment> comments, String category) {
        final CreatePostInputData createInputData = new CreatePostInputData(entryID, author, content,
                timestamp, lastModified, dislikes, likes, postTitle, moderators, comments, category);

        createPostInputBoundary.createPost(createInputData);
    }

    // TODO: need to implmnet in the createpOST usecase
    public void switchToHomePageview() {

    }
}
