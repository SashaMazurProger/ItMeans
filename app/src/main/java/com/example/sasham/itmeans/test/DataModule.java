package com.example.sasham.itmeans.test;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule{

    @SashaQualifier
    @Provides
    User userS(){
        return new User("Sasha");
    }

    @Named("max")
    @Provides
    User userM(){
        return new User("Max");
    }
}
