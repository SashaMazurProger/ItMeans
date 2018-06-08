package com.example.sasham.itmeans.favorites;

import dagger.Component;
import dagger.Subcomponent;

@FavoritesScope
@Subcomponent(modules = {FavoritesModule.class})
public interface FavoritesComponent {
    void inject(FavoritesViewModel favoritesViewModel);
}
