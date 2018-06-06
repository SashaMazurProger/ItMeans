package com.example.sasham.itmeans.test;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

public class DaggerTest {

    @SashaQualifier
    @Inject
    public User user;
}


