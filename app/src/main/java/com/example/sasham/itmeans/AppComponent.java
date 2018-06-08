package com.example.sasham.itmeans;

import com.example.sasham.itmeans.data.network.ApiModule;
import com.example.sasham.itmeans.data.network.db.RealmModule;
import com.example.sasham.itmeans.favorites.FavoritesComponent;
import com.example.sasham.itmeans.favorites.FavoritesModule;
import com.example.sasham.itmeans.search.WordDetailsComponent;
import com.example.sasham.itmeans.search.WordDetailsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class,
        RealmModule.class
})
public interface AppComponent {

    WordDetailsComponent plus(WordDetailsModule wordDetailsModule);
    FavoritesComponent plus(FavoritesModule favoritesModule);
}
