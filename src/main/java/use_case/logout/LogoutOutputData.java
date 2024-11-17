package use_case.logout;

/**
 * Output Data for the Logout Use Case.
 */
public class LogoutOutputData {
    private String email;
    private boolean logoutSuccessful;

    public LogoutOutputData(String email, boolean logoutSuccessful) {
        this.email = email;
        this.logoutSuccessful = logoutSuccessful;
    }

    public String getUserEmail() {
        return email;
    }

    public boolean isLogoutSuccessful() {
        return logoutSuccessful;
    }
}
