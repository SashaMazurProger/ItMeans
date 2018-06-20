package com.example.sasham.itmeans;

import android.content.Context;

import com.example.sasham.itmeans.data.DataRepository;
import com.example.sasham.itmeans.favorites.FavoritesActivity;
import com.example.sasham.itmeans.favorites.FavoritesModule;
import com.example.sasham.itmeans.favorites.FavoritesScope;
import com.example.sasham.itmeans.recents.RecentsActivity;
import com.example.sasham.itmeans.recents.RecentsModule;
import com.example.sasham.itmeans.recents.RecentsScope;
import com.example.sasham.itmeans.search.DetailsScope;
import com.example.sasham.itmeans.search.SearchActivity;
import com.example.sasham.itmeans.search.WordDetailsModule;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@Module(includes = {AndroidSupportInjectionModule.class})
public interface AppModule {

    @DetailsScope
    @ContributesAndroidInjector(modules = {WordDetailsModule.class})
    SearchActivity searchInjector();

    @FavoritesScope
    @ContributesAndroidInjector(modules = {FavoritesModule.class})
    FavoritesActivity favoritesInjector();

    @RecentsScope
    @ContributesAndroidInjector(modules = {RecentsModule.class})
    RecentsActivity recentsInjector();

}
