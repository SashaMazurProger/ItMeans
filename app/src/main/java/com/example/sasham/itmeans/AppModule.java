package com.example.sasham.itmeans;

import android.content.Context;

import com.example.sasham.itmeans.favorites.FavoritesActivity;
import com.example.sasham.itmeans.favorites.FavoritesModule;
import com.example.sasham.itmeans.search.SearchActivity;
import com.example.sasham.itmeans.search.WordDetailsModule;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@Module(includes = {AndroidSupportInjectionModule.class})
public interface AppModule {

    @ContributesAndroidInjector(modules = {WordDetailsModule.class})
    SearchActivity searchInjector();

    @ContributesAndroidInjector(modules = {FavoritesModule.class})
    FavoritesActivity favoritesInjector();
}
