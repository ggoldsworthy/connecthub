package view;

import java.awt.*;
import javax.swing.*;

/**
 * The View for an individual Post.
 */
public class PostView extends JPanel {
    private final JPanel mainContent;
    private final String title;
    private final String content;
    private static final String FONT_TYPE = "Arial";
    private final JPanel postView = new JPanel(new BorderLayout());

    public PostView(JPanel mainContent, String title, String content) {
        this.mainContent = mainContent;
        this.title = title;
        this.content = content;

        postView.setBackground(new Color(120, 133, 133));

        // Post title
        final JPanel titlePanel = new JPanel(new BorderLayout());
        final JLabel postTitle = new JLabel(title);
        postTitle.setFont(new Font(FONT_TYPE, Font.BOLD, 16));
        postTitle.setForeground(Color.DARK_GRAY);
        postTitle.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Add the title to the left side of the title panel
        titlePanel.add(postTitle, BorderLayout.WEST);

        final JButton backButton = new JButton("Back");
        backButton.setFont(new Font(FONT_TYPE, Font.BOLD, 14));
        backButton.setBackground(new Color(200, 200, 200));
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Back button action: Go back to the home page
        backButton.addActionListener(e -> {
            // Return to home
            final CardLayout cl = (CardLayout) mainContent.getLayout();
            cl.show(mainContent, "Homepage");
        });

        // Add the back button to the right side of the title panel
        titlePanel.add(backButton, BorderLayout.EAST);

        // Add title panel to main p
        postView.add(titlePanel, BorderLayout.NORTH);

        // Post content
        final JTextArea postContent = new JTextArea(content);
        postContent.setFont(new Font(FONT_TYPE, Font.PLAIN, 14));
        postContent.setForeground(Color.BLACK);
        postContent.setLineWrap(true);
        postContent.setWrapStyleWord(true);
        postContent.setEditable(false);
        postContent.setBackground(new Color(239, 241, 243));
        postContent.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        postView.add(new JScrollPane(postContent), BorderLayout.CENTER);

        // Comments section
        final JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        commentsPanel.setBackground(Color.WHITE);
        commentsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Comments",
                0,
                0,
                new Font(FONT_TYPE, Font.BOLD, 14),
                Color.DARK_GRAY));

        addComments(commentsPanel);
        final JScrollPane commentsScrollPane = new JScrollPane(commentsPanel);
        commentsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        postView.add(commentsScrollPane, BorderLayout.SOUTH);

        mainContent.add(postView); // Attach postView to mainContent
    }

    private void addComments(JPanel commentsPanel) {
        for (int i = 1; i <= 5; i++) {
            final JLabel comment = new JLabel("Comment " + i + ": This is a sample comment.");
            comment.setFont(new Font(FONT_TYPE, Font.PLAIN, 12));
            comment.setForeground(Color.DARK_GRAY);
            comment.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            commentsPanel.add(comment);
        }
    }

    public JPanel getPostView() {
        return this.postView;
    }
}