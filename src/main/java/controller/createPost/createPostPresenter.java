package controller.createPost;

import controller.ViewManagerModel;
import controller.signup.SignupState;
import use_case.create_post.CreatePostOutputBoundary;
import use_case.create_post.CreatePostOutputData;
;

public class CreatePostPresenter implements CreatePostOutputBoundary {

    private final CreatePostViewModel createPostViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreatePostPresenter(controller.createPost.CreatePostViewModel createPostViewModel, ViewManagerModel viewManagerModel) {
        this.createPostViewModel = createPostViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreatePostOutputData response) {
        final CreatePostState createPostState = createPostViewModel.getState();
        createPostState.setContent(response.getContent());
        this.createPostViewModel.setState(createPostState);
        createPostViewModel.firePropertyChanged();

        viewManagerModel.setState(createPostViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String errorMessage) {
        final CreatePostState createPostState = createPostViewModel.getState();
        createPostState.setPostTitleError(errorMessage);
        createPostViewModel.firePropertyChanged();
    }
}
