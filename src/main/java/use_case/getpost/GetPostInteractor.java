package use_case.getpost;

import entity.Post;
import exception.PostNotFoundException;

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
    public Post getPost(GetPostInputData postInputData) throws PostNotFoundException {
        final String entryID = postInputData.getEntryID();
        try {
            return postDB.getPostByEntryID(entryID);
        }
        catch (PostNotFoundException ex) {
            getPostPresenter.prepareFailView(ex.getMessage());
            throw ex;
        }
    }
}
