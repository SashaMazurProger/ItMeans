package com.example.sasham.itmeans.favorites;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class FavoritesModule {

    @Provides
    public FavoritesInteractor favoritesInteractor(Realm realm){
        return new FavoritesInteractorImp(realm);
    }

    @Provides
    public FavoritesViewModel.Factory factory(FavoritesInteractor favoritesInteractor){
        return new FavoritesViewModel.Factory(favoritesInteractor);
    }
}
