package com.example.sasham.itmeans.data.network.db;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class RealmModule {

    @Provides
    public Realm realm(){
        return Realm.getDefaultInstance();
    }
}
