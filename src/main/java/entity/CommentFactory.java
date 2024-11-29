package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for creating a comment.
 */
public class CommentFactory {
    /**
     * Creates a comment.
     * See Comment and ForumEntry for the meaning of the parameters.
     * @return the Comment object created.
     */
    public Comment createComment(String entryID, User author, String content, String attachmentPath, String fileType) {
        Content commentContent = new CommentContent(content, attachmentPath, fileType);
        List<Comment> replies = new ArrayList<Comment>();
        Comment comment = new Comment(entryID, author.getUserID(), commentContent, LocalDateTime.now(), null, 
                                0, 0, replies);
        return comment;
    }
}
