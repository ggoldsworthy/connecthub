package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import view.StyleConstants;
import javax.swing.*;

/**
 * The Home Page view.
 */
public class HomePage {

    public static void main(String[] args) {

        // Create the main frame
        final JFrame frame = new JFrame("Connect Hub");

        // Terminate program when you click the 'X' button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(StyleConstants.APP_WIDTH, StyleConstants.APP_HEIGHT);
        frame.setLayout(new BorderLayout());

        // Create a CardLayout for the main content area
        // Cardlayout makes sure that new "views" open in the current window
        // Added this so that when you open a post, it can be viewed in the same window
        final JPanel mainContent = new JPanel(new CardLayout());

        // Create individual views
        final JPanel homepageView = createHomepageView(mainContent);
        final JPanel postView = new JPanel(new BorderLayout());

        // Add views to the CardLayout
        mainContent.add(homepageView, "Homepage");
        mainContent.add(postView, "PostView");

        // Nav bar
        // The nav bar has the App name on the left, the search bar in the middle,
        // a search button to the right of the search box, and a profile button on the right most side
        final JPanel navBar = new JPanel(new BorderLayout());
        navBar.setBackground(StyleConstants.HEADER_COLOR);
        navBar.setPreferredSize(new Dimension(frame.getWidth(), 50));

        // Add the app name to the nav bar and allow users to click on it to go to the home page
        final JLabel titleLabel = new JLabel("Connect Hub");
        titleLabel.setForeground(StyleConstants.TEXT_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // Change the cursor to the pointing-hand upon hover
        // Line 69, 70 is from
        // https://stackoverflow.com/questions/16131811/clickable-text-in-a-jtextpane
        titleLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        titleLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                // Go back to the Homepage (Switch to the homepage card)
                final CardLayout cl = (CardLayout) mainContent.getLayout();
                cl.show(mainContent, "Homepage");
            }
        });
        titleLabel.setFont(StyleConstants.HEADER_FONT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(StyleConstants.BORDER_TOP, StyleConstants.BORDER_LEFT, StyleConstants.BORDER_BOTTOM, StyleConstants.BORDER_RIGHT));
        // Add title label to leftmost side of the navbar panel
        navBar.add(titleLabel, BorderLayout.WEST);

        // Search bar
        // Create a panel for the search bar then add this to the nav bar
        final JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(StyleConstants.HEADER_COLOR);
        final JTextField searchBar = new JTextField();
        searchBar.setBackground(StyleConstants.PANEL_COLOR);
        searchBar.setForeground(StyleConstants.TEXT_COLOR);
        searchBar.setCaretColor(StyleConstants.TEXT_COLOR);
        searchBar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        final JButton searchButton = new JButton("Search");
        searchButton.setBackground(StyleConstants.BUTTON_COLOR);
        searchButton.setForeground(StyleConstants.TEXT_COLOR);
        searchPanel.add(searchBar, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        // Add to nav bar
        navBar.add(searchPanel, BorderLayout.CENTER);

        // Profile button
        final JButton profileButton = new JButton("Profile");
        profileButton.setBackground(StyleConstants.BUTTON_COLOR);
        profileButton.setForeground(StyleConstants.TEXT_COLOR);
        navBar.add(profileButton, BorderLayout.EAST);
        // Using lambda expression syntax
        // See https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java
        // profileButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Didn't make user profile view yet"));
        navBar.add(profileButton, BorderLayout.EAST);

        // Add a drop down menu when you click on the User Profile button
        final JPopupMenu profileMenu = new JPopupMenu();
        // Add "Settings" button to the popup menu
        final JMenuItem settingsButton = new JMenuItem("Settings");
        settingsButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Settings clicked"));
        profileMenu.add(settingsButton);

        // Add "Logout" button to the popup menu
        final JMenuItem logoutButton = new JMenuItem("Logout");
        logoutButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Logout clicked"));
        profileMenu.add(logoutButton);

        // Add an ActionListener to the profile button to show the popup menu
        profileButton.addActionListener(e -> {
            profileMenu.show(profileButton, 0, profileButton.getHeight());
        });

        // Make the frame visible
        frame.setVisible(true);

        // Add navigation bar and main content to frame
        frame.add(navBar, BorderLayout.NORTH);
        frame.add(mainContent, BorderLayout.CENTER);

        // Show the frame
        frame.setVisible(true);
    }

    // Create the homepage view
    private static JPanel createHomepageView(JPanel mainContent) {
        final JPanel homepage = new JPanel();
        homepage.setLayout(new BorderLayout());
        homepage.setBackground(StyleConstants.BACKGROUND_COLOR);
        homepage.setLayout(new BorderLayout());

        // Navigation panel for filtering
        final JPanel navigationPanel = createNavigationPane(homepage);
        homepage.add(navigationPanel, BorderLayout.WEST);

        // Content area where we actually populate posts
        final JPanel contentArea = new JPanel();
        contentArea.setLayout(new BoxLayout(contentArea, BoxLayout.Y_AXIS));
        contentArea.setBackground(Color.WHITE);

        // Add mock posts
        // postSpacer separates posts
        postPopulator(contentArea, mainContent);

        final JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        homepage.add(scrollPane, BorderLayout.CENTER);

        // Add a right padding panel to center posts
        final JPanel rightPaddingPanel = new JPanel();
        rightPaddingPanel.setBackground(StyleConstants.PANEL_COLOR);
        rightPaddingPanel.setPreferredSize(new Dimension(147, homepage.getHeight()));
        homepage.add(rightPaddingPanel, BorderLayout.EAST);

        return homepage;
    }

    private static void postPopulator(JPanel panelToPopulate, JPanel mainContent) {
        for (int i = 1; i <= 6; i++) {
            // Can't add the same component multiple times so we must create new postSpacers within the loop
            final JPanel postSpacer = new JPanel();
            postSpacer.setBackground(StyleConstants.PANEL_COLOR);
            postSpacer.setPreferredSize(new Dimension(0, 20));
            panelToPopulate.add(postSpacer);
            panelToPopulate.add(createPostBox("Post Title " + i,
                    "content of post " + i, i, mainContent));
        }
    }

    // Create a post box

    private static JPanel createNavigationPane(JPanel homepage){
        // Navigation panel for filtering
        final JPanel navigationPanel = new JPanel();
        navigationPanel.setBackground(StyleConstants.PANEL_COLOR);
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));
        navigationPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // TODO: Make navigation panel same width as "ConnectHub" on top left
        navigationPanel.setPreferredSize(new Dimension(130, homepage.getHeight()));

        // TODO: Replace with helper that generates a new button per category
        // Buttons
        JButton[] categoryButtons = {
                new JButton("All Posts"),
                new JButton("Category 1"),
                new JButton("Category 2"),
                new JButton("Category 3")
        };
        for (JButton button : categoryButtons) {
            button.setBackground(StyleConstants.BUTTON_COLOR);
            button.setForeground(StyleConstants.TEXT_COLOR);
            button.setFocusPainted(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            navigationPanel.add(Box.createVerticalStrut(10));
            navigationPanel.add(button);
        }

        return navigationPanel;
    }

    private static JPanel createPostBox(String title, String content, int postId, JPanel mainContent) {
        final JPanel postBox = new JPanel();
        postBox.setLayout(new BorderLayout());
        postBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        postBox.setBackground(Color.WHITE);
        postBox.setPreferredSize(new Dimension(StyleConstants.POST_WIDTH, StyleConstants.POST_HEIGHT));

        // Post title
        final JLabel postTitle = new JLabel(title);
        postTitle.setForeground(Color.DARK_GRAY);
        postTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        postBox.add(postTitle, BorderLayout.NORTH);

        // Post content
        final JTextArea postContent = new JTextArea(content);
        postContent.setFont(new Font(StyleConstants.FONT_TYPE, Font.PLAIN, 14));
        postContent.setForeground(Color.BLACK);
        postContent.setLineWrap(true);
        postContent.setWrapStyleWord(true);
        postContent.setEditable(false);

        // GREYISH BACKGROUND FOR EACH POST CHANGE LATER
        postContent.setBackground(new Color(245, 245, 245));
        postContent.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // padding
        postBox.add(postContent, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Spacing from content
        buttonPanel.setBackground(Color.WHITE);

        final JButton likeButton = createStyledButton("Like");
        final JButton dislikeButton = createStyledButton("Dislike");
        final JButton viewPostButton = createStyledButton("View Post");

        likeButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Liked post " + postId));
        dislikeButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Disliked post " + postId));

        // Replace content area with post view when clicked
        viewPostButton.addActionListener(e -> openPostView(mainContent, title, content));

        buttonPanel.add(likeButton);
        buttonPanel.add(dislikeButton);
        buttonPanel.add(viewPostButton);

        postBox.add(buttonPanel, BorderLayout.SOUTH);

        return postBox;
    }

    private static JButton createStyledButton(String text) {
        final JButton button = new JButton(text);
        button.setFont(new Font(StyleConstants.FONT_TYPE, Font.PLAIN, 12));
        // Light gray background for button
        button.setBackground(new Color(230, 230, 230));
        button.setForeground(Color.DARK_GRAY);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                // Padding inside button
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        // Removes the box appearing when clicking on button
        button.setFocusPainted(false);
        return button;
    }

    // Open post view within the same frame
    private static void openPostView(JPanel mainContent, String title, String content) {
        final JPanel postView = new PostView(mainContent, title, content).getPostView();
        final CardLayout layout = (CardLayout) mainContent.getLayout();

        // Move to controller
        mainContent.add(postView, "PostView");
        layout.show(mainContent, "PostView");
    }

}
