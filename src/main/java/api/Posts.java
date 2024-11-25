package api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import use_case.create_post.CreatePostInputBoundary;
import use_case.create_post.CreatePostInputData;
import use_case.create_post.PostCreationFailedException;
import use_case.getpost.GetPostInputBoundary;
import entity.Post;
import entity.User;
import entity.Comment;

@RestController
public class Posts {
    private final GetPostInputBoundary getPostInteractor;
    private final CreatePostInputBoundary createPostInteractor;

    public Posts(GetPostInputBoundary getPostInteractor, CreatePostInputBoundary createPostInteractor) {
        this.getPostInteractor = getPostInteractor;
        this.createPostInteractor = createPostInteractor;
    }

    @GetMapping("/all-posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        // The posts should be paginated in the service. This is a temporary solution.
        List<Post> allPosts = getPostInteractor.getAllPosts();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @PostMapping("/create-post")
    public ResponseEntity<String> createPost(@RequestBody Map<String, Object> requestBody) {
        final CreatePostInputData createPostInputData = new CreatePostInputData(
            (String) requestBody.get("author"),
            (String) requestBody.get("content"),
            (String) requestBody.get("attachment_path"),
            (String) requestBody.get("file_type"),
            (int) requestBody.get("dislikes"),
            (int) requestBody.get("likes"),
            (String) requestBody.get("post_title"),
            new ArrayList<User>(), // TODO temp sol, need changes in the use case: don't have too much time to refactor
            new ArrayList<Comment>(),
            (String) requestBody.get("category")
        );

        try {
            this.createPostInteractor.createPost(createPostInputData);
            return ResponseEntity.status(HttpStatus.OK).body("Post Created!");
        } catch (PostCreationFailedException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.toString());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.toString());
        }
    }

}
