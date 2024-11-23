package controller.post;

import java.util.List;
import java.util.ArrayList;

/**
 * State for the Post view. Contains the data to be displayed in the Post view.
 */
public class PostViewState {
    private final String title;
    private final String content;
    private final List<String> comments;

    public PostViewState(String title, String content) {
        this.title = title;
        this.content = content;
        this.comments = new ArrayList<>(); // Initialize with an empty list of comments
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    // Optionally add a method to load sample comments for testing
    public void loadSampleComments() {
        for (int i = 1; i <= 5; i++) {
            comments.add("Comment " + i + ": This is a sample comment.");
        }
    }
}
