package com.example.sasham.itmeans.test;

import javax.inject.Inject;
import javax.inject.Named;

public class TestRepoImp implements TestRepo {
    int n;
    public User user;

    @Override
    public User getUser() {
        return user;
    }

    @Inject
    public TestRepoImp() {
    }

    public TestRepoImp(@Named("numb") int number, User user) {
        n = number;
        this.user = user;
    }

    @Override
    public int getNumb() {
        return 8;
    }
}
