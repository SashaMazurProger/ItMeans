package com.example.sasham.itmeans.test.arch;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class TestApp extends Application implements HasActivityInjector {

    @Inject
    public DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerTestAppComponent
                .builder()
                .context(this)
                .build()
                .injectApp(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
}

}
