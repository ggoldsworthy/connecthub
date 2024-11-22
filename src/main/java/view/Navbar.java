package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Navbar {
    public static JPanel createNavBar(JFrame frame, JPanel mainContent) {
        final JPanel navBar = new JPanel(new BorderLayout());
        navBar.setBackground(StyleConstants.HEADER_COLOR);
        navBar.setPreferredSize(new Dimension(frame.getWidth(), 50));

        final JLabel titleLabel = new JLabel("Connect Hub");
        titleLabel.setForeground(StyleConstants.TEXT_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // When you hover over the app name, the mouse turns into a hand icon
        // Click enables you to go back to the homepage
        // TODO: Move to ViewManager?
        titleLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        titleLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                final CardLayout cl = (CardLayout) mainContent.getLayout();
                cl.show(mainContent, "Homepage");
            }
        });
        titleLabel.setFont(StyleConstants.HEADER_FONT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(StyleConstants.BORDER_TOP, StyleConstants.BORDER_LEFT,
                StyleConstants.BORDER_BOTTOM, StyleConstants.BORDER_RIGHT));
        navBar.add(titleLabel, BorderLayout.WEST);

        // Add search bar
        final JPanel searchPanel = SearchBar.createSearchPanel();
        navBar.add(searchPanel, BorderLayout.CENTER);

        // Add profile button to nav bar
        final JButton profileButton = ProfileButton.createProfileButton(frame);
        navBar.add(profileButton, BorderLayout.EAST);

        return navBar;
    }
}
