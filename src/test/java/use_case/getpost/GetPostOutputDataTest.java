import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import entity.Content;
import entity.PostContent;
import use_case.getpost.GetPostOutputData;

public class GetPostOutputDataTest {

    private GetPostOutputData getPostOutputData;
    private Content postContent;

    @BeforeEach
    public void setUp() {
        postContent = new PostContent("Body content", "/path/to/attachment", "pdf");
        getPostOutputData = new GetPostOutputData("entry123", postContent);
    }

    @Test
    public void testGetEntryID() {
        assertEquals("entry123", getPostOutputData.getEntryID(), "Entry ID should match.");
    }

    @Test
    public void testGetPostContent() {
        assertEquals(postContent, getPostOutputData.getPostContent(), "Post content should match.");
    }

    // Test hashCode()
    @Test
    public void testHashCode() {
        GetPostOutputData other = new GetPostOutputData("entry123", postContent);
        assertEquals(getPostOutputData.hashCode(), other.hashCode(), "Hash codes should be equal for same data.");
    }

    // Test for equals()
    @Test
    public void testEquals() {
        GetPostOutputData other = new GetPostOutputData("entry123", postContent);
        assertEquals(getPostOutputData, other, "GetPostOutputData should be equal if entryID and content the same.");
    }

    // Test for equals() with different entryID
    @Test
    public void testEqualsWithDifferentEntryID() {
        GetPostOutputData other = new GetPostOutputData("entry456", postContent);
        assertNotEquals(getPostOutputData, other, "GetPostOutputData should not equal if entryID is different.");
    }

    // Test for equals() with different content
    @Test
    public void testEqualsWithDifferentContent() {
        Content otherContent = new PostContent("Different body", "/new/path", "jpg");
        GetPostOutputData other = new GetPostOutputData("entry123", otherContent);
        assertNotEquals(getPostOutputData, other, "GetPostOutputData should not be equal if content is different.");
    }
}
