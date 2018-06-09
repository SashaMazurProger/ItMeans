package com.example.sasham.itmeans;

import android.app.Activity;
import android.app.Application;

import com.example.sasham.itmeans.data.network.ApiModule;
import com.example.sasham.itmeans.data.network.db.RealmModule;
import com.example.sasham.itmeans.favorites.FavoritesComponent;
import com.example.sasham.itmeans.favorites.FavoritesModule;
import com.example.sasham.itmeans.search.WordDetailsComponent;
import com.example.sasham.itmeans.search.WordDetailsModule;

import io.realm.Realm;


public class BaseApplication extends Application {

    private AppComponent appComponent;
    private WordDetailsComponent detailsComponent;
    private FavoritesComponent favoritesComponent;

    public static BaseApplication get(Activity activity) {
        return (BaseApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = createAppComponent();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .realmModule(new RealmModule())
                .build();
    }

    public WordDetailsComponent createDetailsComponent() {
        detailsComponent = appComponent
                .wordDetailsBuilder()
                .wordDetailsModule(new WordDetailsModule())
                .build();
        return detailsComponent;
    }

    public void releaseDetailsComponent() {
        detailsComponent = null;
    }

    public FavoritesComponent createFavoritesComponent() {
        favoritesComponent = appComponent
                .favoritesBuilder()
                .favoritesModule(new FavoritesModule())
                .build();
        return favoritesComponent;
    }

    public void releaseFavoritesComponent() {
        detailsComponent = null;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
