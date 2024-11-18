package controller.editData;

import entity.Comment;
import use_case.EditComment.editDataInterface;

import java.util.ArrayList;

public class editCommentController implements editDataInterface {
    @Override
    public boolean EditContent(String id, String userId, String newContent) {
        // find the comment with the given id
        // need to find reference to user id after they have logged in
        // doesn't seem to be implemented yet
        // Pseudocode: Post post = LoginManager.CurrentlyLoggedInUser.id
        private Comment comment = new Comment();

                // check if the user is authenticated, i.e., they are the author of the post or they are a moderator

                // need to change this to get the actual list of moderators for the given post
                List<String> moderators = new ArrayList<>();

        if (!(userId.equalsIgnoreCase(comment.getAuthor()) || moderators.contains(userId))){
            return false;
        }

        // change the content of the post
        comment.setContent(newContent);

        // update the database with the modified post

        // there currently is no database

        return true;
    }
}
