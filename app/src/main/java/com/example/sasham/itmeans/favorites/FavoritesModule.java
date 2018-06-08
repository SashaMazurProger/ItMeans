package com.example.sasham.itmeans.favorites;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class FavoritesModule {

    @FavoritesScope
    @Provides
    public FavoritesInteractor favoritesInteractor(Realm realm){
        return new FavoritesInteractorImp(realm);
    }
}
