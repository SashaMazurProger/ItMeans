package com.example.sasham.itmeans.test.arch;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = {AndroidSupportInjectionModule.class})
public interface TestAppModule {


    @ContributesAndroidInjector(modules = {UserActivityModule.class})
    UserActivity userActivityInjector();
}
