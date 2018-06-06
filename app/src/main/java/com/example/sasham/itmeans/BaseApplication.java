package com.example.sasham.itmeans;

import android.app.Activity;
import android.app.Application;

import com.example.sasham.itmeans.data.network.ApiModule;
import com.example.sasham.itmeans.search.WordDetailsComponent;
import com.example.sasham.itmeans.search.WordDetailsModule;


public class BaseApplication extends Application {

    private AppComponent appComponent;
    private WordDetailsComponent detailsComponent;

    public static BaseApplication get(Activity activity) {
        return (BaseApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent=createAppComponent();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public WordDetailsComponent createDetailsComponent() {
        detailsComponent=appComponent.plus(new WordDetailsModule());
        return detailsComponent;
    }

    public void releaseDetailsComponent(){
        detailsComponent=null;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
