package view;

import javax.swing.*;
import java.awt.*;

import controller.homepage.HomepageController;
import controller.homepage.HomepageState;
import controller.homepage.HomepageViewModel;
import entity.Post;
import java.util.List;

/**
 * The View for the Home Page.
 */
public class HomePageView {
    private final int PAGE_SIZE = 20;

    private final JPanel mainContent;
    private final HomepageController homepageController;
    private final HomepageViewModel homepageViewModel;
    private final JPanel contentArea;

    public HomePageView(HomepageController homepageController, HomepageViewModel homePageViewModel) {
        this.mainContent = new JPanel(new CardLayout());
        this.homepageController = homepageController;
        this.homepageViewModel = homePageViewModel;

        final JPanel homepage = new JPanel();
        homepage.setLayout(new BorderLayout());
        homepage.setBackground(StyleConstants.BACKGROUND_COLOR);

        // Add nav bar
        final JPanel navigationPanel = NavigationPane.createNavigationPane(homepage);
        homepage.add(navigationPanel, BorderLayout.WEST);

        // Add center panel to display post previews
        final JPanel contentArea = new JPanel();
        contentArea.setLayout(new BoxLayout(contentArea, BoxLayout.Y_AXIS));
        contentArea.setBackground(Color.WHITE);
        this.contentArea = contentArea;

        // Populate the center panel to display posts
        this.loadPosts();

        // Scroll bar
        final JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        homepage.add(scrollPane, BorderLayout.CENTER);

        // Add right panel to center posts and also leave space for future features
        final JPanel rightPaddingPanel = new JPanel();
        rightPaddingPanel.setBackground(StyleConstants.PANEL_COLOR);
        rightPaddingPanel.setPreferredSize(new Dimension(147, homepage.getHeight()));
        homepage.add(rightPaddingPanel, BorderLayout.EAST);

        // Example of observing changes from ViewModel
        homePageViewModel.addPropertyChangeListener(evt -> {
            // Update UI based on new state (e.g., posts updated)
            HomepageState state = homePageViewModel.getState();
            if (state.getPosts() != null) {
                contentArea.removeAll();
                for (String post : state.getPosts()) {
                    contentArea.add(new JLabel(post)); // Simplified example
                }
                contentArea.revalidate();
                contentArea.repaint();
            }
        });
    }

    public void loadPosts() {
        this.contentArea.removeAll();

        List<Post> posts = this.homepageController.fetchPosts(PAGE_SIZE);
        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            final JPanel postSpacer = new JPanel();
            postSpacer.setBackground(StyleConstants.PANEL_COLOR);
            postSpacer.setPreferredSize(new Dimension(0, 20));
            this.contentArea.add(postSpacer);
            this.contentArea.add(new PostBox(post.getPostTitle(), post.getContent().getBody(), 
                i, this.mainContent).getPostBox());
        }
    }
}
