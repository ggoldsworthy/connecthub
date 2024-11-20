package use_case.signup;

import java.util.List;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String username;
    private final String userID;
    private final String password;
    private final String repeatPassword;
    private final String email;
    private final String birthDate;
    private final String fullName;
    private final List<String> moderating;
    private final List<String> posts;

    public SignupInputData(String username, String userID, String password, String repeatPassword, String email, String birthDate, String fullName, List<String> moderating, List<String> posts) {
        this.username = username;
        this.userID = userID;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
        this.birthDate = birthDate;
        this.fullName = fullName;
        this.moderating = moderating;
        this.posts = posts;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserID() {
        return userID;
    }

    public List<String> getModerating() {
        return moderating;
    }

    public List<String> getPosts() {
        return posts;
    }
}
