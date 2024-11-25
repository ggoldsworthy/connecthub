package api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import use_case.getpost.GetPostInputBoundary;
import entity.Post;

@RestController
public class Posts {
    private GetPostInputBoundary getPostInteractor;

    public Posts(GetPostInputBoundary getPostInteractor) {
        this.getPostInteractor = getPostInteractor;
    }

    @GetMapping("/all-posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        // The posts should be paginated in the service. This is a temporary solution.
        List<Post> allPosts = getPostInteractor.getAllPosts();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }
}
