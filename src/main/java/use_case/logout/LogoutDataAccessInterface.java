package use_case.logout;

import entity.User;

public interface LogoutDataAccessInterface {
    /**
     * Returns the email of the curren user of the application.
     * @return the email of the current user
     */
    String getCurrentUserEmail();

    /**
     * Sets the user indicating who is the current user of the application.
     * @param user the new current user
     */
    void setUser(User user);
}
