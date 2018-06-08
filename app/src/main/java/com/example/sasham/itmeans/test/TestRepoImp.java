package com.example.sasham.itmeans.test;

import javax.inject.Inject;

public class TestRepoImp implements TestRepo {

    @Inject
    public TestRepoImp() {
    }

    @Override
    public int getNumb() {
        return 5;
    }
}
