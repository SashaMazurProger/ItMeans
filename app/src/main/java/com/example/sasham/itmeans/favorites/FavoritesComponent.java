package com.example.sasham.itmeans.favorites;

import com.example.sasham.itmeans.search.WordDetailsComponent;
import com.example.sasham.itmeans.search.WordDetailsModule;

import dagger.Component;
import dagger.Subcomponent;

@FavoritesScope
@Subcomponent(modules = {FavoritesModule.class})
public interface FavoritesComponent {

    @Subcomponent.Builder
    interface Builder{
        FavoritesComponent.Builder favoritesModule(FavoritesModule favoritesModule);
        FavoritesComponent build();
    }

    void inject(FavoritesViewModel favoritesViewModel);
}
