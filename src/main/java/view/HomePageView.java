package view;

import javax.swing.*;
import java.awt.*;

public class HomePageView {
    public static JPanel createHomepageView(JPanel mainContent) {
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

        // Populate the centre panel to display posts
        PostPopulator.populate(contentArea, mainContent);

        // Scroll bar
        final JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        homepage.add(scrollPane, BorderLayout.CENTER);

        // Add right panel to centre posts and also leave space for future features
        final JPanel rightPaddingPanel = new JPanel();
        rightPaddingPanel.setBackground(StyleConstants.PANEL_COLOR);
        rightPaddingPanel.setPreferredSize(new Dimension(147, homepage.getHeight()));
        homepage.add(rightPaddingPanel, BorderLayout.EAST);

        return homepage;
    }
}
