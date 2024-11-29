package use_case.editpost;

import daos.DBUserDataAccessObject;
import entity.Post;
import entity.User;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import use_case.edit_post.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

class EditPostInteractorTest {

    private EditPostInteractor interactor;
    private TestEditPostDataAccess stubPostDB;
    private TestEditPostOutput stubPresenter;
    private TestUserRepo stubUserRepo;  // Declare the user repository

    @BeforeEach
    void setUp() {
        stubPostDB = new TestEditPostDataAccess();
        stubPresenter = new TestEditPostOutput();
        stubUserRepo = new TestUserRepo();  // Initialize TestUserRepo

        // Pass all dependencies to the EditPostInteractor constructor
        interactor = new EditPostInteractor(stubPostDB, stubPresenter, stubUserRepo);
    }

    @Test
    void editPostSuccessTest() throws Exception {
        // Arrange
        String entryID = "post123";
        String author = "user123";
        String newTitle = "Updated Title";  // The updated title we're testing
        String newContent = "Updated Content";
        String newAttachmentPath = "/path/to/attachment";
        String fileType = "image/png";  // This is the file type we're updating, not the title
        String category = "General";

        LocalDateTime editDate = LocalDateTime.now();
        stubUserRepo.setCurrentUser(author);

        // Add initial post data to the stub database
        stubPostDB.addPost(entryID, author, "Original Title", "Original Content",
                "/original/path", "text/plain", "General", LocalDateTime.now());

        EditPostInputData inputData = new EditPostInputData(
                entryID, author, newTitle, newContent, newAttachmentPath, fileType, category
        );

        // Act
        interactor.editPost(inputData);

        // Assert
        JSONObject updatedPost = stubPostDB.getPostByEntryID(entryID);

        // Ensure we're comparing the post title correctly
        assertEquals(newTitle, updatedPost.getString("title"));  // Correctly checking the title
        assertEquals(newContent, updatedPost.getString("content_body"));
        assertEquals(newAttachmentPath, updatedPost.getString("attachment_path"));
        assertEquals(fileType, updatedPost.getString("file_type"));
        assertEquals(category, updatedPost.getString("category"));

        EditPostOutputData outputData = stubPresenter.getLastSuccessData();
        assertNotNull(outputData);
        assertEquals(newTitle, outputData.getPostTitle());  // Ensuring the output is the updated title
    }

    @Test
    void editPostFail_NoPermissionTest() {
        // Arrange
        String entryID = "post123";
        String author = "user123";
        String editor = "user456"; // Editor is not the author

        stubUserRepo.setCurrentUser(editor);  // Now set the current user properly

        // Add initial post data to the stub database
        stubPostDB.addPost(entryID, author, "Original Title", "Original Content",
                "/original/path", "text/plain", "General", LocalDateTime.now());

        EditPostInputData inputData = new EditPostInputData(
                entryID, editor, "Updated Title", "Updated Content", "/path/to/attachment", "image/png", "General"
        );

        // Act & Assert
        EditPostFailed exception = assertThrows(EditPostFailed.class, () -> interactor.editPost(inputData));
        assertEquals("You do not have permission to edit this post.", exception.getMessage());

        assertEquals("User does not have permission to edit this post.", stubPresenter.getLastFailMessage());
    }

    @Test
    void editPostInvalidPostIDTest() {
        // Arrange
        String invalidEntryID = "invalid_post123";
        stubUserRepo.setCurrentUser("user123");

        EditPostInputData inputData = new EditPostInputData(
                invalidEntryID, "user123", "Title", "Content", "/path", "text/plain", "Category"
        );

        // Act & Assert
        EditPostFailed exception = assertThrows(EditPostFailed.class, () -> interactor.editPost(inputData));
        assertEquals("Post with entryID invalid_post123 not found.", exception.getMessage());

        assertEquals("Post with entryID invalid_post123 not found.", stubPresenter.getLastFailMessage());
    }

    // === Stub Classes ===

    // Stub for EditPostDataAccessInterface
    static class TestEditPostDataAccess implements EditPostDataAccessInterface {
        private final Map<String, JSONObject> postDatabase = new HashMap<>();

        @Override
        public void updatePost(Post post) {
            try {
                JSONObject updatedPost = new JSONObject();
                updatedPost.put("post_id", post.getEntryID());
                updatedPost.put("author", post.getAuthor());
                updatedPost.put("content_body", post.getContent().getBody());
                updatedPost.put("attachment_path", post.getContent().getAttachmentPath());
                updatedPost.put("file_type", post.getContent().getFileType());
                updatedPost.put("title", post.getPostTitle());
                updatedPost.put("category", post.getCategory());
                postDatabase.put(post.getEntryID(), updatedPost);
            } catch (JSONException e) {
                throw new RuntimeException("Failed to update post due to invalid JSON data.", e);
            }
        }

        @Override
        public JSONObject getPostByEntryID(String postID) {
            if (!postDatabase.containsKey(postID)) {
                throw new RuntimeException("Post with entryID " + postID + " not found.");
            }
            return postDatabase.get(postID);
        }

        public void addPost(String postID, String author, String title, String contentBody, String attachmentPath,
                            String fileType, String category, LocalDateTime lastModified) {
            try {
                JSONObject post = new JSONObject();
                post.put("post_id", postID);
                post.put("author", author);
                post.put("content_body", contentBody);
                post.put("attachment_path", attachmentPath);
                post.put("file_type", fileType);
                post.put("title", title);
                post.put("category", category);
                post.put("last_modified", lastModified.toString());
                postDatabase.put(postID, post);
            } catch (JSONException e) {
                throw new RuntimeException("Failed to add post due to invalid JSON data.", e);
            }
        }
    }

    // Stub for EditPostOutputBoundary
    static class TestEditPostOutput implements EditPostOutputBoundary {
        private String lastFailMessage;
        private EditPostOutputData lastSuccessData;

        @Override
        public void prepareFailView(String errorMessage) {
            this.lastFailMessage = errorMessage;
        }

        @Override
        public void prepareSuccessView(EditPostOutputData outputData) {
            this.lastSuccessData = outputData;
        }

        public String getLastFailMessage() {
            return lastFailMessage;
        }

        public EditPostOutputData getLastSuccessData() {
            return lastSuccessData;
        }
    }

    // Stub for DBUserDataAccessObject
    static class TestUserRepo extends DBUserDataAccessObject {
        private String currentUserID;

        public TestUserRepo() {
            super(null);  // Assuming no database is used for tests
        }

        public void setCurrentUser(String userID) {
            this.currentUserID = userID;
        }

        @Override
        public User getCurrentUser() {
            return new User() {
                @Override
                public String getUsername() {
                    return currentUserID;
                }

                @Override
                public String getPassword() {
                    return "password";  // Stubbed value
                }

                @Override
                public int getAccessLevel() {
                    return 1;  // Stubbed value
                }

                @Override
                public String getEmail() {
                    return "user@example.com";  // Stubbed value
                }

                @Override
                public String getUserID() {
                    return currentUserID;
                }

                @Override
                public String getBirthDate() {
                    return "1990-01-01";  // Stubbed value
                }

                @Override
                public String getFullName() {
                    return "John Doe";  // Stubbed value
                }

                @Override
                public List<String> getModerating() {
                    return List.of();  // Stubbed value
                }

                @Override
                public List<String> getPosts() {
                    return List.of();  // Stubbed value
                }
            };
        }
    }
}
