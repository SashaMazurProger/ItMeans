package com.example.sasham.itmeans;

import android.support.test.rule.ActivityTestRule;

import com.example.sasham.itmeans.favorites.FavoritesActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;


public class FavoritesActivityTest {

    @Rule
    public ActivityTestRule<FavoritesActivity> testRule=new ActivityTestRule<>(FavoritesActivity.class);


    @Test
    public void onCreate() {

    }
}