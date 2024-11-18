package entity;

import java.util.ArrayList;

/**
 * Factory for creating CommonUser objects.
 */
public class ModeratorUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, String userID, String birthDate, String fullName, String email, ArrayList moderating, ArrayList posts) {
        return new ModeratorUser(name, password, userID, birthDate, fullName, email);
    }
}
