package app;

import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoCollection;

import api.Authentification;
import controller.ViewManagerModel;
import controller.signup.SignupPresenter;
import controller.signup.SignupViewModel;
import daos.DBUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;

@Configuration
public class AppConfig {

    @Bean
    public Repositories repositories() {
        return new Repositories();
    }

    @Bean
    public MongoCollection<Document> userRepository(Repositories repositories) {
        return repositories.getUserRepository();
    }

    // DAOs
    @Bean
    public DBUserDataAccessObject userDAO(MongoCollection<Document> userRepository) {
        return new DBUserDataAccessObject(userRepository);
    }

    // Factories
    @Bean
    public UserFactory userFactory() {
        return new CommonUserFactory();
    }

    // View Models
    @Bean 
    public ViewManagerModel viewManagerModel() {
        return new ViewManagerModel();
    }
    
    @Bean
    public SignupViewModel signupViewModel() {
        return new SignupViewModel();
    }

    // Presenters
    @Bean
    public SignupOutputBoundary signupPresenter(SignupViewModel signupViewModel) {
        return new SignupPresenter(null, signupViewModel); // Adjust dependencies if needed
    }

    @Bean
    public SignupInputBoundary signupInteractor(DBUserDataAccessObject userDAO,
                                                SignupOutputBoundary signupPresenter,
                                                UserFactory userFactory) {
        return new SignupInteractor(userDAO, signupPresenter, userFactory);
    }

    // RestAPIs
    @Bean
    public Authentification authentification(SignupInputBoundary signupInteractor) {
        return new Authentification(signupInteractor);
    }
}