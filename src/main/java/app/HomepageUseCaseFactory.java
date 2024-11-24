package app;

import controller.ViewManagerModel;
import controller.homepage.HomepageController;
import controller.homepage.HomepagePresenter;
import controller.homepage.HomepageViewModel;
import daos.DBPostDataAccessObject;
import use_case.getpost.GetPostInputBoundary;
import use_case.getpost.GetPostInteractor;
import use_case.getpost.GetPostOutputBoundary;
import view.HomePageView;

public class HomepageUseCaseFactory {
    public HomepageUseCaseFactory() {

    }

    public static HomePageView create(
        ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel,
        DBPostDataAccessObject postDAO
    ) {
        final HomepageController homepageController = createHomepageController(viewManagerModel, homepageViewModel, postDAO);
        return new HomePageView(homepageController, homepageViewModel);
    }

    public static HomepageController createHomepageController(
        ViewManagerModel viewManagerModel, HomepageViewModel homepageViewModel, 
        DBPostDataAccessObject postDAO
    ) {
        final GetPostOutputBoundary homepagePresenter = new HomepagePresenter(viewManagerModel, homepageViewModel);
        
        final GetPostInputBoundary getPostInteractor = new GetPostInteractor(postDAO, homepagePresenter);
        
        return new HomepageController(getPostInteractor);
    }
}
