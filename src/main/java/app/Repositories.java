package app;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Repositories {
    private MongoClient mongoClient;
    private MongoCollection<Document> userRepository;
    private MongoCollection<Document> postRepository;
    private MongoCollection<Document> commentRepository;

    public Repositories() {
        // Connecting to the database
		// TODO It's a good practice to put the connectionString to a .env file, will do later.
		String connectionString = "mongodb+srv://connecthub:csc207@connecthub.rdlr3.mongodb.net/?retryWrites=true&w=majority&appName=ConnectHub";
        
		ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        
		MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        
        try {
            // Create a new client and connect to the server
            MongoClient mongoClient = MongoClients.create(settings);
            this.mongoClient = mongoClient;

            // Send a ping to confirm a successful connection
            MongoDatabase database = mongoClient.getDatabase("ConnectHub");
            
            MongoCollection<Document> userRepository = database.getCollection("Users");
            MongoCollection<Document> postRepository = database.getCollection("Posts");
            MongoCollection<Document> commentRepository = database.getCollection("Comments");
            
            this.userRepository = userRepository;
            this.postRepository = postRepository;
            this.commentRepository = commentRepository;
            
            System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
        } catch (MongoException e) {
            e.printStackTrace();
        }
	}

    public void closeDatabaseConnection() {
        if (this.mongoClient != null) {
            mongoClient.close();
        }
    }

    public MongoCollection<Document> getUserRepository() {
        return this.userRepository;
    }

    public MongoCollection<Document> getPostRepository() {
        return this.postRepository;
    }

    public MongoCollection<Document> getCommentRepository() {
        return this.commentRepository;
    }
}
