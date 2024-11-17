package use_case.logout;

public class LogoutInputData {

    private final String email;

    public LogoutInputData(String email) {
        this.email = email;
    }

    public String getUserEmail() {
        return email;
    }

}
