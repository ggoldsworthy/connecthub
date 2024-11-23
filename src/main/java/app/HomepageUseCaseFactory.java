package app;

import controller.ViewManagerModel;
import controller.homepage.HomepageViewModel;
import view.HomePageView;

import javax.swing.*;
import java.awt.*;

public class HomepageUseCaseFactory {

    public static JPanel create(ViewManagerModel viewManagerModel, HomepageViewModel homePageViewModel) {
        final JPanel mainContent = new JPanel(new CardLayout());
        return HomePageView.createHomepageView(mainContent, homePageViewModel);
    }
}
