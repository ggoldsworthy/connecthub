package use_case.signup;

/**
 * Exception thrown when a user already exists.
 */
public class UserExistsException extends Exception {
    public UserExistsException(String email) {
        super("User with email " + email + " already exists.");
    }
}
