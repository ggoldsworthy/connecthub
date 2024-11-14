package use_case.logout;

import entity.User;

public interface LogoutDataAccessInterface {
    /**
     * Returns the username of the curren user of the application.
     * @return the username of the current user
     */
    String getCurrentUsername();

    /**
     * Sets the user indicating who is the current user of the application.
     * @param user the new current user
     */
    void setUser(User user);
}
