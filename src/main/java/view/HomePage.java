package view;

import javax.swing.*;
import java.awt.*;

// JUST FOR TESTING PURPOSES SHOULD BE IN MAIN APP FOLDER/CONNECTHUB.JAVA INSTEAD
public class HomePage {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Connect Hub");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(StyleConstants.APP_WIDTH, StyleConstants.APP_HEIGHT);
        frame.setLayout(new BorderLayout());

        final JPanel mainContent = new JPanel(new CardLayout());
        final JPanel homepageView = HomePageView.createHomepageView(mainContent);
        final JPanel postView = new JPanel(new BorderLayout());

        mainContent.add(homepageView, "Homepage");
        mainContent.add(postView, "PostView");

        final JPanel navBar = Navbar.createNavBar(frame, mainContent);

        frame.add(navBar, BorderLayout.NORTH);
        frame.add(mainContent, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
