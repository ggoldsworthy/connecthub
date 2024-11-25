package app;

import controller.homepage.HomepageViewModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.bson.Document;
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

@SpringBootApplication
@ComponentScan({"app", "api"})
public class WebServer {
    public static void main(String[] args) {
        final Repositories repositories = new Repositories();
		final MongoCollection<Document> userRepository = repositories.getUserRepository();
		final MongoCollection<Document> postRepository = repositories.getPostRepository();

		// DAOs
		final DBUserDataAccessObject userDAO = new DBUserDataAccessObject(userRepository);
		final DBPostDataAccessObject postDAO = new DBPostDataAccessObject(postRepository);

		// Entity Factories
		UserFactory commonUserFactory = new CommonUserFactory();

		// Views (irrelavent for a web application)
		final ViewManagerModel viewManagerModel = new ViewManagerModel();
        final SignupViewModel signupViewModel = new SignupViewModel();
		final LoginViewModel loginViewModel = new LoginViewModel();
		final HomepageViewModel homepageViewModel = new HomepageViewModel();
		final PostViewModel postViewModel = new PostViewModel();

		// Presenters (irrelavent for a web application)
		final SignupOutputBoundary signupPresenter = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);
		final LoginOutputBoundary loginPresenter = new LoginPresenter(viewManagerModel, homepageViewModel, loginViewModel, signupViewModel);
		final GetPostOutputBoundary homepagePresenter = new HomepagePresenter(viewManagerModel, homepageViewModel, postViewModel);

		// Service Interactors
		final SignupInputBoundary signUpInteractor = new SignupInteractor(userDAO, signupPresenter, commonUserFactory);
		final LoginInputBoundary loginInteractor = new LoginInteractor(userDAO, loginPresenter, commonUserFactory);
		final GetPostInputBoundary getPostInteractor = new GetPostInteractor(postDAO, homepagePresenter);

		// RestAPI
		new Authentification(signUpInteractor, loginInteractor);
		new Posts(getPostInteractor);

		// Application Start
        SpringApplication.run(WebServer.class, args);

        Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				repositories.closeDatabaseConnection();
				System.out.println("Discconected to the database.");
			}
		});
    }
}
