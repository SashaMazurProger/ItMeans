package com.example.sasham.itmeans.recents;

import javax.inject.Inject;

public class RecentsPresenterImp implements RecentsPresenter {

    private RecentsView recentsView;
    private RecentsInteractor recentsInteractor;

    @Inject
    public RecentsPresenterImp(RecentsView recentsView, RecentsInteractor recentsInteractor) {
        this.recentsView = recentsView;
        this.recentsInteractor = recentsInteractor;
    }

    @Override
    public void fetchWords() {

        recentsView.onLoading();

        recentsView.showWords(recentsInteractor.getAllRecentWords());

        recentsView.onLoaded();
    }

    @Override
    public void destroy() {
        recentsInteractor.destroy();
    }
}
