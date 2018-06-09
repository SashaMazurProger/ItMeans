package com.example.sasham.itmeans.test.arch;

import com.example.sasham.itmeans.test.TestRepo;
import com.example.sasham.itmeans.test.TestRepoImp;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class UserActivityModule {

    @Binds
    abstract TestRepo testRepo(TestRepoImp testRepo);
}
