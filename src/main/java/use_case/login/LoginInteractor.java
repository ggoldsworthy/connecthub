package use_case.login;

import entity.CommonUser;
import entity.User;
import entity.CommonUserFactory;
import org.json.JSONObject;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements use_case.login.LoginInputBoundary {
    private final use_case.login.LoginDataAccessInterface loginDB;
    private final LoginOutputBoundary loginOutput;

    public LoginInteractor(LoginDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.loginDB = userDataAccessInterface;
        this.loginOutput = loginOutputBoundary;
    }

    @Override
    public void LoginUser(use_case.login.LoginInputData loginInputData) {
        final String email = loginInputData.getEmail();
        final String password = loginInputData.getPassword();
        if (!loginDB.existByEmail(email)) {
            loginOutput.prepareFailView(email + ": Account does not exist.");
        }
        else {
            final String pwd = loginDB.getUserByEmail(email).getString("email");
            if (!password.equals(pwd)) {
                loginOutput.prepareFailView("Incorrect password for \"" + email + "\".");
            }
            else {
                final JSONObject user = loginDB.getUserByEmail(loginInputData.getEmail());
                loginDB.setCurrentUser(jsonObjectToUser(user));
                final LoginOutputData loginOutputData = new LoginOutputData(
                        user.getString("username"), user.getString("password"), true);
                loginOutput.prepareSuccessView(loginOutputData);
            }
        }
    }

    //private User jsonObjectToUser(JSONObject user) {
        //final String username = user.getString("username");

        //final User u = new CommonUser(user.getString("username"),user.getString("password"),user.getString("userId"),
        //        user.getString("birth_date"), user.getString("full_name"), user.getString("email"),
        //        user.getJSONArray("moderating"), user.getJSONArray("posts"));
        //return u;

    //}
}