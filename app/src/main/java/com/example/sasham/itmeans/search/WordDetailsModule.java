package com.example.sasham.itmeans.search;

import com.example.sasham.itmeans.data.network.TwinWordApi;

import dagger.Module;
import dagger.Provides;

@Module
public class WordDetailsModule {

    @DetailsScope
    @Provides
    public WordDetailsInteractor wordRepository(TwinWordApi twinWordApi) {
        return new WordDetailsInteractorImp(twinWordApi);
    }

}
