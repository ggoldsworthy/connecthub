package view;

import javax.swing.*;
import java.awt.*;

public class HomePage {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Connect Hub");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(StyleConstants.APP_WIDTH, StyleConstants.APP_HEIGHT);
        frame.setLayout(new BorderLayout());

        final JPanel mainContent = new JPanel(new CardLayout());

        // Create an instance of HomepageViewModel
        final controller.homepage.HomepageViewModel homepageViewModel = new controller.homepage.HomepageViewModel();

        // Pass mainContent and homepageViewModel to createHomepageView
        final JPanel homepageView = HomePageView.createHomepageView(mainContent, homepageViewModel);

        final JPanel postView = new JPanel(new BorderLayout());

        mainContent.add(homepageView, "Homepage");
        mainContent.add(postView, "PostView");

        final JPanel navBar = Navbar.createNavBar(frame, mainContent);

        frame.add(navBar, BorderLayout.NORTH);
        frame.add(mainContent, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
