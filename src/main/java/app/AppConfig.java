package app;

import controller.homepage.HomepageViewModel;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoCollection;

import api.Authentification;
import api.Posts;
import controller.ViewManagerModel;
import controller.homepage.HomepagePresenter;
import controller.homepage.HomepageViewModel;
import controller.login.LoginPresenter;
import controller.login.LoginViewModel;
import controller.post.PostViewModel;
import controller.signup.SignupPresenter;
import controller.signup.SignupViewModel;
import daos.DBPostDataAccessObject;
import daos.DBUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import use_case.getpost.GetPostInputBoundary;
import use_case.getpost.GetPostInteractor;
import use_case.getpost.GetPostOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;

@Configuration
public class AppConfig {

    @Bean
    public Repositories repositories() {
        return new Repositories();
    }

    @Bean
    public MongoCollection<Document> userRepository(Repositories repositories) {
        return repositories.getUserRepository();
    }

    @Bean
    public MongoCollection<Document> postRepository(Repositories repositories) {
        return repositories.getPostRepository();
    }

    // DAOs
    @Bean
    public DBUserDataAccessObject userDAO(MongoCollection<Document> userRepository) {
        return new DBUserDataAccessObject(userRepository);
    }

    @Bean
    public DBPostDataAccessObject postDAO(MongoCollection<Document> postRepository) {
        return new DBPostDataAccessObject(postRepository);
    }

    // Factories
    @Bean
    public UserFactory userFactory() {
        return new CommonUserFactory();
    }

    // View Models
    @Bean
    public ViewManagerModel viewManagerModel() {
        return new ViewManagerModel();
    }

    @Bean
    public SignupViewModel signupViewModel() {
        return new SignupViewModel();
    }

    @Bean
    public LoginViewModel loginViewModel() {
        return new LoginViewModel();
    }

    @Bean
    public HomepageViewModel homepageViewModel(){
        return new HomepageViewModel();
    }

    @Bean
    public PostViewModel postViewModel() {
        return new PostViewModel();
    }

    // Presenters
    @Bean
    public SignupOutputBoundary signupPresenter(ViewManagerModel viewManagerModel,
                                                SignupViewModel signupViewModel,
                                                LoginViewModel loginViewModel) {
        return new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel); // Adjust dependencies if needed
    }

    @Bean
    public LoginOutputBoundary loginPresenter(ViewManagerModel viewManagerModel,
                                              HomepageViewModel homepageViewModel,
                                              LoginViewModel loginViewModel,
                                              SignupViewModel signupViewModel) {
        return new LoginPresenter(viewManagerModel, homepageViewModel, loginViewModel, signupViewModel);
    }

    @Bean
    public GetPostOutputBoundary homepagePresenter(ViewManagerModel viewManagerModel,
                                                   HomepageViewModel homepageViewModel,
                                                   PostViewModel postViewModel) {
        return new HomepagePresenter(viewManagerModel, homepageViewModel, postViewModel);
    }

    // Services
    @Bean
    public SignupInputBoundary signupInteractor(DBUserDataAccessObject userDAO,
                                                SignupOutputBoundary signupPresenter,
                                                UserFactory userFactory) {
        return new SignupInteractor(userDAO, signupPresenter, userFactory);
    }

    @Bean
    public LoginInputBoundary loginInteractor(DBUserDataAccessObject userDAO,
                                              LoginOutputBoundary loginPresenter,
                                              UserFactory userFactory) {
        return new LoginInteractor(userDAO, loginPresenter, userFactory);
    }

    @Bean
    public GetPostInputBoundary getPostInteractor(DBPostDataAccessObject postDAO,
                                                  GetPostOutputBoundary homepagePresenter) {
        return new GetPostInteractor(postDAO, homepagePresenter);
    }

    // RestAPIs
    @Bean
    public Authentification authentification(SignupInputBoundary signupInteractor,
                                             LoginInputBoundary loginInteractor) {
        return new Authentification(signupInteractor, loginInteractor);
    }

    @Bean
    public Posts posts(GetPostInputBoundary getPostInteractor) {
        return new Posts(getPostInteractor);
    }
}