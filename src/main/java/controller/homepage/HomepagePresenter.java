package controller.homepage;

import controller.ViewManagerModel;
import use_case.getpost.GetPostOutputBoundary;
import use_case.getpost.GetPostOutputData;

public class HomepagePresenter implements GetPostOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final HomepageViewModel homepageViewModel;

    // TODO will need a home page view model
    public HomepagePresenter(ViewManagerModel viewManagerModel,
                             HomepageViewModel homepageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
    }
    
    /**
     * Prepares the success view for the Get Post Use Case.
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(GetPostOutputData outputData) {
        final HomepageState homepageState = this.homepageViewModel.getState();
        homepageState.setPosts(outputData.getAllPosts().subList(0, 10));
        this.homepageViewModel.setState(homepageState);
        this.homepageViewModel.firePropertyChanged();

        this.viewManagerModel.setState(homepageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Get Post Use Case.
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        final HomepageState homepageState = this.homepageViewModel.getState();
        homepageState.setPostsError(null);
        this.homepageViewModel.firePropertyChanged();
    }
}
