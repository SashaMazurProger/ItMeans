package com.example.sasham.itmeans.recents;

import dagger.Binds;
import dagger.Module;

@Module
public interface RecentsFragmentModule {

    @RecentsFragmentScope
    @Binds
    RecentsView recentView(RecentsFragment recentsFragment);

    @RecentsFragmentScope
    @Binds
    RecentsPresenter recentsPresenter(RecentsPresenterImp recentsFragment);
}
