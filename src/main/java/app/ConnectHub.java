package app;

import javax.swing.*;

import com.mongodb.client.MongoCollection;
import controller.post.PostViewModel;
import org.bson.Document;
import daos.DBUserDataAccessObject;
import controller.ViewManagerModel;
import daos.DBUserDataAccessObject;
import entity.CommonUserFactory;
import controller.ViewManagerModel;
import controller.homepage.HomepageViewModel;
//import controller.login.LoginViewModel;
//import view.LoginUseCaseFactory;
import view.*;

import java.util.List;
import java.awt.*;

/**
 * The version of Main with an external database used to persist user data.
 */
public class ConnectHub {

	/**
	 * The main method for starting the program with an external database used to persist user data.
	 * @param args input to main
	 */
	public static void main(String[] args) {
		final JFrame application = new JFrame("ConnectHub");

		// Close the app when clicking X
		application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Set window size
		application.setSize(StyleConstants.APP_WIDTH, StyleConstants.APP_HEIGHT);

		// cardLayout is assigned to views which manages different views
		final CardLayout cardLayout = new CardLayout();
		final JPanel views = new JPanel(cardLayout);

		// Add views to the main application frame
		application.add(views);

		// View manager manages the current view
		final ViewManagerModel viewManagerModel = new ViewManagerModel();
		new ViewManager(views, cardLayout, viewManagerModel);

//		final LoginViewModel loginViewModel = new LoginViewModel();

		// Add the homepage view model
		final HomepageViewModel homePageViewModel = new HomepageViewModel();
//		final PostViewModel postPageViewModel = new PostViewModel();
//		final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(new CommonUserFactory());

		// Create and add views
//		views.add(LoginUseCaseFactory.create(viewManagerModel, loginViewModel, homePageViewModel, userDataAccessObject),
//				"login");

		// Add the built homepage to the views panel, and label the card "home"
		views.add(HomepageUseCaseFactory.create(viewManagerModel, homePageViewModel), "home");

		// Set the initial view to the home page
		viewManagerModel.setState("home");
		// Swtich to the homepage
		viewManagerModel.firePropertyChanged();

		// THIS IS JUST DUMMY POSTS
		// TODO: Replace with Posts from MongoDB
		List<String> dummyTitles = List.of("Post 1", "Post 2", "Post 3");
		List<String> dummyContents = List.of(
				"This is the content of the first post.",
				"This is the content of the second post.",
				"This is the content of the third post."
		);

			// Create and add the PostBoxes with dummy data
		for (int i = 0; i < dummyTitles.size(); i++) {
			String title = dummyTitles.get(i);
			String content = dummyContents.get(i);

			// Create a PostViewModel for each dummy post
			PostViewModel postViewModel = new PostViewModel(title, content);

			// Pass the 'views' panel and the 'postViewModel' to the PostView constructor
			views.add(new PostView(views, postViewModel), title); // Use the title as a unique identifier for each post
		}


		// Create and add the navigation bar
		final JPanel navBar = Navbar.createNavBar(application, views);
		application.add(navBar, BorderLayout.NORTH);
		application.pack();
		application.setVisible(true);
	}
}
