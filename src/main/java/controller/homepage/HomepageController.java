package controller.homepage;

import use_case.getpost.GetPostInputBoundary;

public class HomepageController {
    private final GetPostInputBoundary getPostInteractor;

    public HomepageController(GetPostInputBoundary getPostInteractor) {
        this.getPostInteractor = getPostInteractor;
    }

    public void fetchPosts(int size) {
        this.getPostInteractor.getAllPosts(); 
    }

    public void switchToLoginView() {
        this.getPostInteractor.switchToPostView();
    }
}
