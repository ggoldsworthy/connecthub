package use_case.logout;

/**
 * Output Data for the Logout Use Case.
 */
public class LogoutOutputData {
    private String username;
    private boolean logoutSuccessful;

    public LogoutOutputData(String username, boolean logoutSuccessful) {
        this.username = username;
        this.logoutSuccessful = logoutSuccessful;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLogoutSuccessful() {
        return logoutSuccessful;
    }
}
