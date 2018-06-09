package com.example.sasham.itmeans.test;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class DataModule {

    @SashaQualifier
    @Provides
    static User userS() {
        return new User("Sasha");
    }

    @Named("max")
    @Provides
    static User userM() {
        return new User("Max");
    }

    @Named("numb")
    @Provides
    static int numb(){return 6;}

//    @Binds
//    public abstract TestRepo testRepo(TestRepoImp testRepoImp);
}
