package use_case.logout;

public class LogoutInputData {

    private final String userID;

    public LogoutInputData(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

}
