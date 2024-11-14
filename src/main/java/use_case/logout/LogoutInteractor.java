package use_case.logout;

import org.apache.juli.logging.Log;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary{
    private LogoutDataAccessInterface logoutDB;
    private LogoutOutputBoundary logoutOutput;

    public LogoutInteractor(LogoutDataAccessInterface logoutDB, LogoutOutputBoundary logoutOutput) {
        this.logoutDB = logoutDB;
        this.logoutOutput = logoutOutput;
    }

    @Override
    public void LogoutUser(LogoutInputData logoutInputData) {
        final String username = logoutInputData.getUsername();
        logoutDB.setUser(null);
        LogoutOutputData logoutOutputData = new LogoutOutputData(username, true);
        logoutOutput.prepareSuccessView(logoutOutputData);
    }
}
