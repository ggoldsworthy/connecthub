package use_case.getpost;

import entity.Post;

/**
 * The Get Post Interactor.
 */
public class GetPostInteractor implements GetPostInputBoundary {
    private final GetPostInputData getPostInputData;
    private final GetPostDataAccessInterface postDB;
    private final GetPostOutputBoundary getPostPresenter;

    public GetPostInteractor(GetPostInputData getPostInputData,
                             GetPostDataAccessInterface postDB,
                             GetPostOutputBoundary getPostPresenter) {
        this.getPostInputData = getPostInputData;
        this.postDB = postDB;
        this.getPostPresenter = getPostPresenter;
    }

    @Override
    public Post getPost(GetPostInputData postInputData) throws Exception {
        final String entryID = postInputData.getEntryID();
        return postDB.getPostByEntryID(entryID);
    }

}
