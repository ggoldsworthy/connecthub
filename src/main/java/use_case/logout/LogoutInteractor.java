package use_case.logout;

import org.apache.juli.logging.Log;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private LogoutDataAccessInterface logoutDB;
    private LogoutOutputBoundary logoutOutput;

    public LogoutInteractor(LogoutDataAccessInterface logoutDB, LogoutOutputBoundary logoutOutput) {
        this.logoutDB = logoutDB;
        this.logoutOutput = logoutOutput;
    }

    @Override
    public void logoutUser(LogoutInputData logoutInputData) {
        // * get the userEmail out of the input data,
        // * set the userEmail to null in the DAO,
        // * instantiate the 'LogoutOutputData', which needs to contain the userEmail,
        // * tell the presenter to prepare a success view.
        final String email = logoutInputData.getUserEmail();
        logoutDB.setUser(null);
        LogoutOutputData logoutOutputData = new LogoutOutputData(email, true);
        logoutOutput.prepareSuccessView(logoutOutputData);
    }
}
