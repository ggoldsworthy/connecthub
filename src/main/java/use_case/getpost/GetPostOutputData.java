package use_case.getpost;

import entity.Content;

/**
 * The Input Data for the Get Post Use Case.
 */
public class GetPostOutputData {

    private final String entryID;
    private final Content postContent;

    public GetPostOutputData(String entryID, Content postContent) {
        this.entryID = entryID;
        this.postContent = postContent;
    }

    public String getEntryID() {
        return entryID;
    }

    public Content getPostContent() {
        return postContent;
    }
}
