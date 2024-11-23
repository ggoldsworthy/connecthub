package controller.post;

import controller.ViewModel;
import java.util.List; // Ensure this is imported

/**
 * ViewModel for individual Post views. Extends ViewModel with PostState.
 */
public class PostViewModel extends ViewModel<PostViewState> {

    public PostViewModel(String title, String content) {
        super("post view");
        // Create PostState instance and set it as the state for this ViewModel
        PostViewState postState = new PostViewState(title, content);
        postState.loadSampleComments();  // Load sample comments
        setState(postState);
    }

    public String getTitle() {
        return getState().getTitle();
    }

    public String getContent() {
        return getState().getContent();
    }

    public List<String> getComments() {
        return getState().getComments();
    }

    public void addComment(String comment) {
        getState().addComment(comment);
        firePropertyChanged();
    }
}
