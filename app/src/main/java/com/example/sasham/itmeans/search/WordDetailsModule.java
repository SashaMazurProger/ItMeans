package com.example.sasham.itmeans.search;

import com.example.sasham.itmeans.data.DataRepository;
import com.example.sasham.itmeans.data.network.TwinWordApi;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class WordDetailsModule {

    @DetailsScope
    @Provides
    public WordDetailsInteractor wordRepository(Realm realm, TwinWordApi twinWordApi, DataRepository dataRepository){
        return new WordDetailsInteractorImp(twinWordApi,realm, dataRepository);
    }

    @DetailsScope
    @Provides
    public WordDetailsViewModel.Factory factory(WordDetailsInteractor wordDetailsInteractor){
        return new WordDetailsViewModel.Factory(wordDetailsInteractor);
    }
}
