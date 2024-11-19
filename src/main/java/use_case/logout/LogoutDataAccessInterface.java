package use_case.logout;

import entity.User;

public interface LogoutDataAccessInterface {
    /**
     * Returns the email of the current user of the application.
     * @return the email of the current user
     */
    String getCurrentUserEmail();

    /**
     * log out the user by setting the current User to null
     */
    void logoutUser();
}
