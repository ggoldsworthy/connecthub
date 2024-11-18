package daos;

import entity.Comment;
import entity.Post;
import entity.PostContent;
import entity.PostFactory;
// TODO rename to whatever package/class name created by others
import use_case.create_post.CreatePostDataAccessInterface;
import use_case.delete_post.DeletePostDataAccessInterface;
import use_case.update_post.UpdatePostDataAccessInterface;
import use_case.get_post.GetPostDataAccessInterface;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.InsertOneResult;
import static com.mongodb.client.model.Filters.eq;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * MongoDB implementation of the DAO for storing user data. 
 */
public class DBPostDataAccessObject implements CreatePostDataAccessInterface,
                                               DeletePostDataAccessInterface,
                                               UpdatePostDataAccessInterface,
                                               GetPostDataAccessInterface {
    private final String ENTRY_ID = "post_id";
    private final String AUTHOR = "author";
    private final String CONTENT_BODY = "content_body";
    private final String ATTACHMENT_PATH = "attachment_path";
    private final String FILE_TYPE = "file_type";
    private final String POST_TITLE = "title";
    private final String CATEGORY = "category";
    private final String POSTED_DATE = "author";
    private final String LAST_MODIFIED = "last_modified";
    private final String LIKES = "likes";
    private final String DISLIKES = "dislikes";
    private final String COMMENTS = "comments";

    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private MongoCollection<Document> postRepository;

    public DBPostDataAccessObject(MongoCollection<Document> postRepository) {
        this.postRepository = postRepository;
    }

    // TODO check if there's any other operations missing

    @Override
    public void createPost(Post post) {
        this.insertPostToDB(post);
    }

    @Override
    public Post getPostByID(String id) {
        return this.createPost(queryOnePostBy(ENTRY_ID, id));
    }

    @Override
    public List<Post> getPostsByCategory(String category, int postSize) {
        return null; // TODO
    }

    @Override
    public List<Post> getPostsByTime(int postSize) { // TODO figure out the time stamp if we want this method
        return null;
    }

    @Override 
    public void deletePost(Post post, String postID) {
        // TODO
    }

    @Override
    public void updatePost(Post updatedContent) {
        // TODO
    }

    
    /**
     * Inserts the given post into the database.
     * @param user - a user in the application.
     */
    private void insertPostToDB(Post post) {
        try {
            
            Document data = (new Document()
                .append(ENTRY_ID, post.getEntryID())
                .append(AUTHOR, post.getAuthor())
                .append(CONTENT_BODY, post.getContent().getBody())
                .append(ATTACHMENT_PATH, post.getContent().getAttachmentPath())
                .append(FILE_TYPE, post.getContent().getFileType())
                .append(POST_TITLE, post.getPostTitle())
                .append(CATEGORY, post.getCategory())
                .append(POSTED_DATE, post.getPostedDate())
                .append(LAST_MODIFIED, post.getLastModifiedDate())
                .append(LIKES, post.getLikes())
                .append(DISLIKES, post.getDislikes())
                .append(COMMENTS, post.getComments()) // TODO figure out type conversions if neccessary
            );

            InsertOneResult result = this.postRepository.insertOne(data);
            System.out.println("Successfully inserted post with insert id: " + result.getInsertedId());
        } catch (MongoException err) {
            // TODO throws custom exceptions when they're created
        }
    }

    /**
     * Queries a specific post from the database.
     * @param field - the column to to match.
     * @param target - the target value to query for.
     */
    private Document queryOnePostBy(String field, String target) {
        Bson projectionFields = Projections.fields(
            Projections.include(field),
            Projections.excludeId()
        );
        
        Document doc = this.postRepository
            .find(eq(field, target))
            .projection(projectionFields)
            .first();

        return doc;
    }

    /**
     * Creates a post to return when querying the database.
     * @param userData - the data for a post.
     */
    private Post createPost(Document postData) {
    //     Document data = (new Document()
    //     .append(ENTRY_ID, post.getEntryID())
    //     .append(AUTHOR, post.getAuthor())
    //     .append(CONTENT_BODY, post.getContent().getBody())
    //     .append(ATTACHMENT_PATH, post.getContent().getAttachmentPath())
    //     .append(FILE_TYPE, post.getContent().getFileType())
    //     .append(POST_TITLE, post.getPostTitle())
    //     .append(CATEGORY, post.getCategory())
    //     .append(POSTED_DATE, post.getPostedDate())
    //     .append(LAST_MODIFIED, post.getLastModifiedDate())
    //     .append(LIKES, post.getLikes())
    //     .append(DISLIKES, post.getDislikes())
    //     .append(COMMENTS, post.getComments()) // TODO figure out type conversions if neccessary
    // );
        final String content = postData.getString(CONTENT_BODY);
        final String attachmentPath = postData.getString(ATTACHMENT_PATH);
        final String fileType = postData.getString(FILE_TYPE);

        return new Post(
            postData.getString(ENTRY_ID),
            postData.getString(AUTHOR),
            new PostContent(content, attachmentPath, fileType),
            LocalDateTime.parse(postData.getString(POSTED_DATE), DATE_TIME_FORMATTER),
            LocalDateTime.parse(postData.getString(LAST_MODIFIED), DATE_TIME_FORMATTER),
            postData.getInteger(LIKES),
            postData.getInteger(DISLIKES),
            postData.getString(POST_TITLE),
            postData.getList(COMMENTS, Comment.class),
            postData.getString(CATEGORY)
        );
    }
}
