package view;

import controller.post.PostViewModel;

import java.awt.*;
import javax.swing.*;
import java.util.List; // Ensure this is imported

/**
 * The View for an individual Post.
 */
public class PostView extends JPanel {
    private final JPanel mainContent;
    private final PostViewModel viewModel;

    private static final String FONT_TYPE = "Arial";
    private final JPanel postView = new JPanel(new BorderLayout());

    public PostView(JPanel mainContent, PostViewModel viewModel) {
        this.mainContent = mainContent;
        this.viewModel = viewModel;

        setLayout(new BorderLayout());
        setBackground(new Color(120, 133, 133));

        add(createTitlePanel(), BorderLayout.NORTH);
        add(createContentPanel(), BorderLayout.CENTER);
        add(createCommentsPanel(), BorderLayout.SOUTH);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel postTitle = new JLabel(viewModel.getTitle());
        postTitle.setFont(new Font(FONT_TYPE, Font.BOLD, 16));
        postTitle.setForeground(Color.DARK_GRAY);
        postTitle.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        titlePanel.add(postTitle, BorderLayout.WEST);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font(FONT_TYPE, Font.BOLD, 14));
        backButton.setBackground(new Color(200, 200, 200));
        backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        backButton.addActionListener(e -> {
            CardLayout layout = (CardLayout) mainContent.getLayout();
            layout.show(mainContent, "Homepage");
        });

        titlePanel.add(backButton, BorderLayout.EAST);
        return titlePanel;
    }

    private JScrollPane createContentPanel() {
        JTextArea postContent = new JTextArea(viewModel.getContent());
        postContent.setFont(new Font(FONT_TYPE, Font.PLAIN, 14));
        postContent.setForeground(Color.BLACK);
        postContent.setLineWrap(true);
        postContent.setWrapStyleWord(true);
        postContent.setEditable(false);
        postContent.setBackground(new Color(239, 241, 243));
        postContent.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        return new JScrollPane(postContent);
    }

    private JScrollPane createCommentsPanel() {
        JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        commentsPanel.setBackground(Color.WHITE);
        commentsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Comments",
                0,
                0,
                new Font(FONT_TYPE, Font.BOLD, 14),
                Color.DARK_GRAY));

        // Ensure the getComments method returns a List<String>
        List<String> comments = viewModel.getComments();
        for (String comment : comments) {
            JLabel commentLabel = new JLabel(comment);
            commentLabel.setFont(new Font(FONT_TYPE, Font.PLAIN, 12));
            commentLabel.setForeground(Color.DARK_GRAY);
            commentLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            commentsPanel.add(commentLabel);
        }

        return new JScrollPane(commentsPanel);
    }

}