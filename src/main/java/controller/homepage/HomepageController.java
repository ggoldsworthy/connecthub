package controller.homepage;

import java.util.List;

import org.json.JSONArray;

import entity.Post;
import use_case.getpost.GetPostInputBoundary;

public class HomepageController {
    private final GetPostInputBoundary getPostInteractor;

    public HomepageController(GetPostInputBoundary getPostInteractor) {
        this.getPostInteractor = getPostInteractor;
    }

    public List<Post> fetchPosts(int size) {
        List<Post> posts = this.getPostInteractor.fetchPosts(); // TODO edit
        return posts.subList(size);
    }
}
