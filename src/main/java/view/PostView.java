package view;

import controller.post.PostController;
import controller.post.PostState;
import controller.post.PostViewModel;
import controller.signup.SignupState;
import use_case.getpost.GetPostInputBoundary;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import java.util.List;

public class PostView extends JPanel implements PropertyChangeListener {
    private final String viewName = "post";
    private final PostController postController;
    private final PostViewModel postViewModel;
    
    private final String FONT_TYPE = "Arial";
    private final JPanel mainContent = new JPanel(new BorderLayout());
    private final JLabel postTitle = new JLabel();
    private final JTextArea postContent = new JTextArea();
    private final JPanel commentsPanel = new JPanel();

    public PostView(PostController postController, PostViewModel postViewModel) {
        this.postController = postController;
        this.postViewModel = postViewModel;
        postViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setBackground(new Color(120, 133, 133)); // TODO change to constants HEADER

        // Add navbar
        // final JPanel navBar = Navbar.createNavBar(mainContent);
        // add(navBar, BorderLayout.NORTH);

        // Post title
        JPanel titlePanel = new JPanel(new BorderLayout());
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

        // Post content
        postContent.setFont(new Font(FONT_TYPE, Font.PLAIN, 14));
        postContent.setForeground(Color.BLACK);
        postContent.setLineWrap(true);
        postContent.setWrapStyleWord(true);
        postContent.setEditable(false);
        postContent.setBackground(new Color(239, 241, 243));
        postContent.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        // Post comment
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        commentsPanel.setBackground(Color.WHITE);
        commentsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Comments",
                0,
                0,
                new Font(FONT_TYPE, Font.BOLD, 14),
                Color.DARK_GRAY));
            
        add(titlePanel, BorderLayout.NORTH);
        add(new JScrollPane(postContent), BorderLayout.CENTER);
        add(new JScrollPane(commentsPanel), BorderLayout.SOUTH);
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final PostState state = (PostState) evt.getNewValue();
        if (state.getPostContentError() != null) {
            JOptionPane.showMessageDialog(this, state.getPostContentError());
        } else if (state.getCommentsError() != null) {
            JOptionPane.showMessageDialog(this, state.getPostContent());
        }
    }

    public void setPostTitle(PostState state) {
        this.postTitle.setText(state.getPostTitle());
    }

    private void setPostContent() {
    }

    private void setComments() {


        // List<String> comments = viewModel.getComments();
        // for (String comment : comments) {
        //     JLabel commentLabel = new JLabel(comment);
        //     commentLabel.setFont(new Font(FONT_TYPE, Font.PLAIN, 12));
        //     commentLabel.setForeground(Color.DARK_GRAY);
        //     commentLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        //     commentsPanel.add(commentLabel);
        // }
    }
}