package entity;

import java.util.ArrayList;

/**
 * Factory for creating CommonUser objects.
 */
public class AnonymousUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, String userID, String birthDate, String fullName, String email, ArrayList moderating, ArrayList posts) {
        return new AnonymousUser(name, password, userID, birthDate, fullName, email);
    }
}
