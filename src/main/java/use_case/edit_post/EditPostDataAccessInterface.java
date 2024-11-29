package use_case.edit_post;

import org.json.JSONObject;

import entity.Post;

public interface EditPostDataAccessInterface {

    void updatePost(Post post);

    JSONObject getPostByEntryID(String postID);
}

// create instance of this interface where you provide the body of this method
