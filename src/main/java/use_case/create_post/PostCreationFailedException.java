package use_case.create_post;

public class PostCreationFailedException extends RuntimeException {
    public PostCreationFailedException(String err) {
        super(err);
    }
}
