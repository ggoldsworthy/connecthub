package use_case.logout;

import org.json.JSONObject;

import entity.User;

public interface LogoutDataAccessInterface {
    /**
     * log out the user by setting the current User to null
     */
    void logoutUser();

    public JSONObject getUserById(String userID);    

    public JSONObject getUserByUsername(String username);

    public JSONObject getUserByEmail(String email);    

    public User getCurrentUser();

    public void setCurrentUser(User currentUser);
}
