package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import controller.homepage.HomepageState;
import controller.homepage.HomepageViewModel;

/**
 * The View for the Home Page.
 */
public class HomePageView {
    private ArrayList<PostBox> posts;

    public HomePageView() {
        this.posts = new ArrayList<PostBox>();
    }

    public static JPanel createHomepageView(JPanel mainContent, HomepageViewModel homePageViewModel) {
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

        // Populate the center panel to display posts
        PostPopulator.populate(contentArea, mainContent);

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

        return homepage;
    }

    public ArrayList<PostBox> getPostContainers() {
        return this.posts;
    }
}
