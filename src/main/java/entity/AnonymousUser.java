package entity;

/**
 * A simple implementation of the User interface.
 */
public class AnonymousUser implements User {

    private final String name;
    private final String password;
    private final int accessLevel;
    private final String userID;
    private final String birthDate;
    private final String fullName;
    private final String email;

    public AnonymousUser(String name, String password, String userID, String birthDate, String fullName, String email) {
        this.name = name;
        this.password = password;
        this.userID = userID;
        this.birthDate = birthDate;
        this.fullName = fullName;
        this.email = email;
        this.accessLevel = 0;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public int getAccessLevel() {return accessLevel; }

    public String getUserID() {
        return userID;
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
}
