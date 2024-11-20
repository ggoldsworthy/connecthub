package use_case.signup;

public class AccountDoesNotExistException extends RuntimeException {
    public AccountDoesNotExistException(String msg) {
        super(msg);
    }
}
