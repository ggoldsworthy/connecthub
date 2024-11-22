package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ProfileButton {
    public static JButton createProfileButton(JFrame frame) {
        final JButton profileButton = new JButton("Profile");
        profileButton.setBackground(StyleConstants.BUTTON_COLOR);
        profileButton.setForeground(StyleConstants.TEXT_COLOR);

        final JPopupMenu profileMenu = new JPopupMenu();
        final JMenuItem settingsButton = new JMenuItem("Settings");
        settingsButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Settings clicked"));
        profileMenu.add(settingsButton);

        final JMenuItem logoutButton = new JMenuItem("Logout");
        logoutButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Logout clicked"));
        profileMenu.add(logoutButton);

        profileButton.addActionListener(e -> profileMenu.show(profileButton, 0, profileButton.getHeight()));

        return profileButton;
    }
}
