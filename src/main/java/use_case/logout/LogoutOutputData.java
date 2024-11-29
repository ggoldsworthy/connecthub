package use_case.logout;

/**
 * Output Data for the Logout Use Case.
 */
public class LogoutOutputData {
    private String userID;
    private boolean logoutSuccessful;

    public LogoutOutputData(String userID, boolean logoutSuccessful) {
        this.userID = userID;
        this.logoutSuccessful = logoutSuccessful;
    }

    public String getUserID() {
        return userID;
    }

    public boolean isLogoutSuccessful() {
        return logoutSuccessful;
    }
}
