package com.example.sasham.itmeans;

import android.content.Intent;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.example.sasham.itmeans.search.SearchActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.*;

public class SearchActivityTest {

    @Rule
    public ActivityTestRule<SearchActivity> testRule = new ActivityTestRule<>(SearchActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void onCreate() {
        onView(withId(R.id.noun_title)).check(matches(withText("Noun")));
        onView(withId(R.id.et)).perform(typeText("Haha"));
        onView(withId(R.id.b)).perform(click());
    }

    @Test
    public void onCreateOptionsMenu() {
        onView(withId(R.id.action_search)).perform(typeText("mask"));

    }

    @Test
    public void searchWord() {
        Intent intent=new Intent(SearchActivity.SEARCH_WORD);
        intent.putExtra(SearchActivity.STRING_WORD_EXTRA,"car");
        testRule.launchActivity(intent);
        onView(withId(R.id.entry)).check(matches(withText("car")));
    }
}