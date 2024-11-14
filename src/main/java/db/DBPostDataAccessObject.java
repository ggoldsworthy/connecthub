package db;

import entity.Post;
import use_case.post.PostDataAccessInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * In-memory implementation of the DAO for storing post data.
 * This implementation does NOT persist data between runs of the program.
 */
public class DBPostDataAccessObject implements PostDataAccessInterface {

    private final Map<String, Post> posts = new HashMap<>();

    @Override
    public Post getPostById(String postId) {
        return posts.get(postId);
    }

    @Override
    public List<Post> getAllPostsByUser(String userId) {
        List<Post> userPosts = new ArrayList<>();
        for (Post post : posts.values()) {
            if (post.getUserId().equals(userId)) {
                userPosts.add(post);
            }
        }
        return userPosts;
    }

    @Override
    public void savePost(Post post) {
        posts.put(post.getId(), post);
    }

    @Override
    public boolean deletePost(String postId) {
        return posts.remove(postId) != null;
    }
}
