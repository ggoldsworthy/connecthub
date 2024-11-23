package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import daos.DBPostDataAccessObject;
import daos.DBUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import controller.ViewManagerModel;
import controller.login.LoginViewModel;
import controller.post.PostState;
import controller.post.PostViewModel;
//import controller.logged_in.LoggedInViewModel;
//import controller.login.LoginViewModel;
import controller.signup.SignupViewModel;
import view.PostView;
//import view.LoggedInView;
//import view.LoginView;
import view.SignupView;
import view.ViewManager;

/**
 * The version of Main with an external database used to persist user data.
 */
public class ConnectHub {

	/**
	 * The main method for starting the program with an external database used to persist user data.
	 * @param args input to main
	 */
	public static void main(String[] args) {
		final Repositories repositories = new Repositories();
		final MongoCollection<Document> userRepository = repositories.getUserRepository();
		final MongoCollection<Document> postRepository = repositories.getPostRepository();
		final MongoCollection<Document> commentRepositroy = repositories.getCommentRepository();

		final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userRepository);
		final DBPostDataAccessObject postDataAccessObject = new DBPostDataAccessObject(postRepository);

		// Closes the connection with the database when the program terminates
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				repositories.closeDatabaseConnection();
				System.out.println("Discconected to the database.");
			}
		});

		// Build the main program window, the main panel containing the
		// various cards, and the layout, and stitch them together.

		// The main application window.
		final JFrame application = new JFrame("ConnectHub");
		application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		final CardLayout cardLayout = new CardLayout();

		// The various View objects. Only one view is visible at a time.
		final JPanel views = new JPanel(cardLayout);
		application.add(views);

		// This keeps track of and manages which view is currently showing.
		final ViewManagerModel viewManagerModel = new ViewManagerModel();
		new ViewManager(views, cardLayout, viewManagerModel);

		// The data for the views, such as username and password, are in the ViewModels.
		// This information will be changed by a presenter object that is reporting the
		// results from the use case. The ViewModels are "observable", and will
		// be "observed" by the Views.
//		final LoginViewModel loginViewModel = new LoginViewModel();
//		final LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
		final SignupViewModel signupViewModel = new SignupViewModel();
		final LoginViewModel loginViewModel = new LoginViewModel();
		final PostViewModel postViewModel = new PostViewModel();

		final SignupView signupView = SignupUseCaseFactory.create(viewManagerModel,
				signupViewModel, loginViewModel, userDataAccessObject);
		views.add(signupView, signupView.getViewName());

//		final LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel,
//				loggedInViewModel, userDataAccessObject);
//		views.add(loginView, loginView.getViewName());
//
//		final LoggedInView loggedInView = ChangePasswordUseCaseFactory.create(viewManagerModel,
//				loggedInViewModel, userDataAccessObject);
//		views.add(loggedInView, loggedInView.getViewName());
		
		final PostView postView = GetPostUseCaseFactory.create(viewManagerModel, postViewModel, postDataAccessObject);
		views.add(postView, postView.getViewName());

		viewManagerModel.setState(signupView.getViewName());
		// viewManagerModel.setState(postView.getViewName());
		viewManagerModel.firePropertyChanged();

		application.pack();
		application.setVisible(true);
	}
}
