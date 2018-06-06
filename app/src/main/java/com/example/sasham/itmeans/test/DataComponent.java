package com.example.sasham.itmeans.test;

import dagger.Component;

@Component(modules = {DataModule.class})
public interface DataComponent{
    void inject(DaggerTest daggerTest);
}
