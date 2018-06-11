package com.example.sasham.itmeans.recents;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public interface RecentsModule {

    @RecentsFragmentScope
    @ContributesAndroidInjector(modules = {RecentsFragmentModule.class})
    RecentsFragment recentsFragmentInjector();
}
