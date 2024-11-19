package entity;

import java.util.ArrayList;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getUsername();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the access level of the user.
     * @return the access level of the user.
     */
    int getAccessLevel();

    String getEmail();

    String getUserID();

    String getBirthDate();

    String getFullName();

    public ArrayList<String> getModerating();

    public ArrayList<String> getPosts();
}
