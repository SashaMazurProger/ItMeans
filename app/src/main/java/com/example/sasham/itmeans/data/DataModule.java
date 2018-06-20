package com.example.sasham.itmeans.data;

import com.example.sasham.itmeans.data.network.TwinWordApi;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class DataModule {

    @Provides
    public DataRepository dataRepository(Realm realm, TwinWordApi twinWordApi){
        return new DataRepository(twinWordApi,realm);
    }
}
