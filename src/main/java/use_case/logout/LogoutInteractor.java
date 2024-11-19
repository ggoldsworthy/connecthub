package use_case.logout;
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
        // * get the userID out of the input data,
        // * set the user to null in the DAO,
        // * instantiate the 'LogoutOutputData', which needs to contain the userID,
        // * tell the presenter to prepare a success view.
        final String userID = logoutInputData.getUserID();
        logoutDB.logoutUser();
        LogoutOutputData logoutOutputData = new LogoutOutputData(userID, true);
        logoutOutput.prepareSuccessView(logoutOutputData);
    }
}
