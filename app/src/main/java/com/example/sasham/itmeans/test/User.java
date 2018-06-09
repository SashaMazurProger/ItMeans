package com.example.sasham.itmeans.test;

import javax.inject.Inject;

public class User{
    public String name;

    @Inject
    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
