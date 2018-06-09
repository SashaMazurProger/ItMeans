package com.example.sasham.itmeans.test.arch;

import android.content.Context;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;

@Component(modules = {TestAppModule.class})
public interface TestAppComponent{

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder context(Context context);
        TestAppComponent build();
    }

    void injectApp(TestApp testApp);
}
