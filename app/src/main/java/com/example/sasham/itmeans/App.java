package com.example.sasham.itmeans;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {
//    @Inject
//    private AndroidInjector<Activity> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return null;
    }
}
