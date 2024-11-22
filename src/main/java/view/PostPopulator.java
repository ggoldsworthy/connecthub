package view;

import javax.swing.*;
import java.awt.*;

public class PostPopulator {
    public static void populate(JPanel panelToPopulate, JPanel mainContent) {
        for (int i = 1; i <= 6; i++) {
            final JPanel postSpacer = new JPanel();
            postSpacer.setBackground(StyleConstants.PANEL_COLOR);
            postSpacer.setPreferredSize(new Dimension(0, 20));
            panelToPopulate.add(postSpacer);
            panelToPopulate.add(new PostBox("Post Title " + i,
                    "content of post " + i, i, mainContent).getPostBox());
        }
    }
}
