package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import controller.homepage.HomepageController;
import controller.homepage.HomepageState;
import controller.homepage.HomepageViewModel;
import entity.Post;
import java.util.List;

/**
 * The View for the Home Page.
 */
public class HomePageView extends JPanel implements PropertyChangeListener {
    private final String viewName = "home page";
    private final int PAGE_SIZE = 5;

    private final JPanel homepage = new JPanel();
    private final JPanel contentArea = new JPanel();
    private final JPanel rightPaddingPanel = new JPanel();

    private final HomepageController homepageController;
    private final HomepageViewModel homepageViewModel;

    public HomePageView(HomepageController homepageController, HomepageViewModel homePageViewModel) {
        this.homepageController = homepageController;
        this.homepageViewModel = homePageViewModel;
        homePageViewModel.addPropertyChangeListener(this);

        this.homepage.setLayout(new BorderLayout());
        this.homepage.setBackground(StyleConstants.BACKGROUND_COLOR);

        // Add navigation pane
        final JPanel navigationPanel = NavigationPane.createNavigationPane(homepage);
        homepage.add(navigationPanel, BorderLayout.WEST);

        // Add center panel to display post previews
        this.contentArea.setLayout(new BoxLayout(contentArea, BoxLayout.Y_AXIS));
        this.contentArea.setBackground(Color.WHITE);

        // Scroll bar
        final JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        homepage.add(scrollPane, BorderLayout.CENTER);

        // Add right panel to center posts and also leave space for future features
        this.rightPaddingPanel.setBackground(StyleConstants.PANEL_COLOR);
        this.rightPaddingPanel.setPreferredSize(new Dimension(147, homepage.getHeight()));
        homepage.add(rightPaddingPanel, BorderLayout.EAST);

        add(homepage);

        // Example of observing changes from ViewModel
        // homePageViewModel.addPropertyChangeListener(evt -> {
        //     // Update UI based on new state (e.g., posts updated)
        //     HomepageState state = homePageViewModel.getState();
        //     if (state.getPosts() != null) {
        //         contentArea.removeAll();
        //         for (String post : state.getPosts()) {
        //             contentArea.add(new JLabel(post)); // Simplified example
        //         }
        //         contentArea.revalidate();
        //         contentArea.repaint();
        //     }
        // });
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }

    public void loadPosts(List<Post> posts) {
        this.contentArea.removeAll();
        for (Post post : posts) {
            this.contentArea.add(new PostBox(
                post.getPostTitle(), post.getContent().getBody(), post.getEntryID(), homepage).getPostBox());            
        }
    }

    public String getViewName() {
        return viewName;
    }
}
