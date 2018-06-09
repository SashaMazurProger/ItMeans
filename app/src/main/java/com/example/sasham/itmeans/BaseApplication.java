package com.example.sasham.itmeans;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;


public class BaseApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    public static BaseApplication get(Activity activity) {
        return (BaseApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        injectApp();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
    }

    private void injectApp() {
        DaggerAppComponent.builder()
                .context(this)
                .build()
                .injectApp(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
