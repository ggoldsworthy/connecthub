package entity;

import java.util.ArrayList;

/**
 * A simple implementation of the User interface.
 */
public class AnonymousUser implements User {

    private final String username;
    private final String password;
    private final int accessLevel;
    private final String userID;
    private final String birthDate;
    private final String fullName;
    private final String email;
    private final ArrayList<String> moderators;
    private final ArrayList<String> posts;



    public AnonymousUser(String name, String password, String userID, String birthDate, String fullName, String email, ArrayList<String> moderators, ArrayList<String> posts) {
        this.username = name;
        this.password = password;
        this.userID = userID;
        this.birthDate = birthDate;
        this.fullName = fullName;
        this.email = email;
        this.moderators = moderators;
        this.posts = posts;
        this.accessLevel = 0;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getAccessLevel() {
        return accessLevel;
    }

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

    public ArrayList<String> getModerators() {
        return moderators;
    }

    public ArrayList<String> getPosts() {
        return posts;
    }
}
