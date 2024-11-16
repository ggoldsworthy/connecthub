package daos;

import entity.CommonUser;
import entity.User;
import entity.UserFactory;
import use_case.signup.SignupDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import use_case.logout.LogoutDataAccessInterface;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.InsertOneResult;
import static com.mongodb.client.model.Filters.eq;

/**
 * MongoDB implementation of the DAO for storing user data. 
 */
public class DBUserDataAccessObject implements SignupDataAccessInterface,
                                               LoginDataAccessInterface,
                                               LogoutDataAccessInterface {
    private final String USER_ID = "userId";
    private final String USER_NAME = "username";
    private final String PASSWORD = "password";
    private final String EMAIL = "email";
    private final String BIRTH_DATE = "birth_date";
    private final String FULL_NAME = "full_name";
    private final String MODERATING = "moderating";
    private final String POSTS = "posts";

    private MongoCollection<Document> userRepository;
    private UserFactory commonUserFactory;
    private User currentUser;

    public DBUserDataAccessObject(MongoCollection<Document> userRepository, UserFactory commonUserFactory) {
        this.userRepository = userRepository;
        this.commonUserFactory = commonUserFactory;
    }

    @Override
    public void save(User user) {
        this.insertUserToDB(user);
    }
    
    @Override
    public boolean existsByID(String userID) {
        return this.queryOneUserBy(USER_ID, userID) == null;
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.queryOneUserBy(USER_NAME, username) == null;
    }


    @Override
    public boolean existsByEmail(String email) {
        return this.queryOneUserBy(EMAIL, email) == null;
    }

    @Override
    public User getUserById(String userID) {
        return this.createUser(this.queryOneUserBy(USER_ID, userID));
    }
    
    @Override
    public User getUserByUsername(String username) {
        return this.createUser(this.queryOneUserBy(USER_NAME, username));
    }

    @Override
    public User getUserByEmail(String email) {
        return this.createUser(this.queryOneUserBy(EMAIL, email));
    }
    
    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override 
    public void logoutUser() {
        this.setCurrentUser(null);
    }

    /**
     * Inserts the given user into the database.
     * @param user - a user in the application.
     */
    private void insertUserToDB(User user) {
        try {
            Document data = new Document(); // temporary data placeholder
            // TODO most of the user.get methods don't exists yet, need to wait for Izabelle to finish it up. Commenting this so program can build
            
            // Document data = new Document()
            //     .append(USER_ID, user.getUserID())
            //     .append(USER_NAME, user.getUserName())
            //     .append(PASSWORD, user.getPassword())
            //     .append(EMAIL, user.getEmail())
            //     .append(BIRTH_DATE, user.getBirthDate())
            //     .append(FULL_NAME, user.getFullName())
            //     .append(MODERATING, user.getModerating())
            //     .append(POSTS, user.getPosts()
            // );

            InsertOneResult result = this.userRepository.insertOne(data);
            System.out.println("Successfully inserted user with insert id: " + result.getInsertedId());
        } catch (MongoException err) {
            // TODO throws custom exceptions when they're created
        }
    }

    /**
     * Queries a specific user from the database.
     * @param field - the column to to match.
     * @param target - the target value to query for.
     */
    private Document queryOneUserBy(String field, String target) {
        Bson projectionFields = Projections.fields(
            Projections.include(field),
            Projections.excludeId()
        );
        
        Document doc = this.userRepository
            .find(eq(field, target))
            .projection(projectionFields)
            .first();

        return doc;
    }

    /**
     * Creates a CommonUser to return when querying the database.
     * @param userData - the data for a user.
     */
    private User createUser(Document userData) {
        // TODO the order of the parameters may need to change depending on how Izabelle implemennts the factory
        // return this.commonUserFactory.create(
        //     userData.getString(USER_ID),
        //     userData.getString(USER_NAME),
        //     userData.getString(PASSWORD),
        //     userData.getString(EMAIL),
        //     userData.getString(BIRTH_DATE),
        //     userData.getString(FULL_NAME),
        //     userData.getString(MODERATING), // TODO may need to do type conversion, need more details form izabell
        //     userData.getString(POSTS) // ^
        // );
        return null;
    }
}
