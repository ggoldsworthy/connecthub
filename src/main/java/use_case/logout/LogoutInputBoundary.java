package use_case.logout;

public interface LogoutInputBoundary {

    /**
     * LogoutUser
     * @param LogoutInputData the input data
     */
    void LogoutUser(LogoutInputData LogoutInputData);
}
