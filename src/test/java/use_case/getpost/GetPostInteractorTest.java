package use_case.getpost;

import entity.*;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetPostInteractorTest {

    private GetPostDataAccessInterface mockPostDB;
    private GetPostOutputBoundary mockPresenter;
    private GetPostInteractor interactor;

    @BeforeEach
    void setUp() {
        mockPostDB = Mockito.mock(GetPostDataAccessInterface.class);
        mockPresenter = Mockito.mock(GetPostOutputBoundary.class);
    }

//    @Test
//    void GetPostSuccessTest() throws Exception {
//        String entryID = "123";
//        User author = new CommonUser("User One", "1234", "1", "bday", "name", "user1@example.com", null, null);
//        Content content = new PostContent("This is a sample post content.", null, null);
//        LocalDateTime postedDate = LocalDateTime.now();
//        LocalDateTime lastModifiedDate = LocalDateTime.now();
//        Post expectedPost = new Post(
//                entryID,
//                author.getUserID(),
//                content,
//                postedDate,
//                lastModifiedDate,
//                10,
//                2,
//                "Sample Post Title",
//                Collections.emptyList(),
//                "General"
//        );
//
//        when(mockPostDB.getPostByEntryID(entryID)).thenReturn(expectedPost);
//
//        GetPostInputData inputData = new GetPostInputData(entryID);
//        interactor = new GetPostInteractor(mockPostDB, mockPresenter);
//
//        Post result = interactor.getPost(inputData);
//
//        assertEquals(expectedPost, result);
//        verify(mockPostDB).getPostByEntryID(entryID);
//        // Check that presenter was called
//        verify(mockPresenter).prepareSuccessView(new GetPostOutputData(entryID, content));
//    }
//
//    @Test
//    void GetPostPostNotFoundTest() throws Exception {
//        String entryID = "123";
//        String expectedMessage = "Post with entryID " + entryID + " not found.";
//        when(mockPostDB.getPostByEntryID(entryID)).thenThrow(new PostNotFoundException(entryID));
//
//        GetPostInputData inputData = new GetPostInputData(entryID);
//        interactor = new GetPostInteractor(mockPostDB, mockPresenter);
//
//        Post result = interactor.getPost(inputData);
//
//        assertNull(result);
//        verify(mockPostDB).getPostByEntryID(entryID);
//        verify(mockPresenter).prepareFailView(expectedMessage);
//    }
//
//    @Test
//    void GetPostInvalidInputTest() {
//        GetPostInputData inputData = new GetPostInputData(null);
//        interactor = new GetPostInteractor(mockPostDB, mockPresenter);
//
//        assertThrows(IllegalArgumentException.class, () -> interactor.getPost(inputData));
//    }

    @Test
    void GetPostPresenterCalledOnSuccessTest() throws Exception {
        String entryID = "123";
        User author = new CommonUser("User One", "1234", "1", "bday", "name", "user1@example.com", null, null);
        Content content = new PostContent("This is a sample post content.", "test/path", "type");
        ArrayList<Comment> comments = new ArrayList<>();
        LocalDateTime postedDate = LocalDateTime.now();
        LocalDateTime lastModifiedDate = LocalDateTime.now();

        Post expectedPost = new Post(
                entryID,
                author.getUserID(),
                content,
                postedDate,
                lastModifiedDate,
                10,
                2,
                "Sample Post Title",
                comments,
                "General"

        );

        JSONObject jsonExpected = new JSONObject();
        jsonExpected.put("post_id", entryID);
        jsonExpected.put("author", author.getUsername());
        jsonExpected.put("content_body", content.getBody());
        jsonExpected.put("attachment_path", content.getAttachmentPath());
        jsonExpected.put("file_type", "type");
        jsonExpected.put("title", "Sample Post Title");
        jsonExpected.put("category", "General");
        jsonExpected.put("posted_date", postedDate);
        jsonExpected.put("last_modified", lastModifiedDate);
        jsonExpected.put("likes", 10);
        jsonExpected.put("dislikes", 2);
        jsonExpected.put("comments", comments);
        jsonExpected.put("likes", 3);

        when(mockPostDB.getPostByEntryID(entryID)).thenReturn(jsonExpected);

        GetPostInputData inputData = new GetPostInputData(entryID);
        interactor = new GetPostInteractor(mockPostDB, mockPresenter);

        GetPostOutputData outputData = new GetPostOutputData(entryID, content);

        Post result = interactor.getPost(inputData);

        assertEquals(expectedPost, result);

        verify(mockPresenter).prepareSuccessView(outputData);
    }
}