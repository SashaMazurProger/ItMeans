package com.example.sasham.itmeans;

import android.content.Intent;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.text.TextUtils;

import com.example.sasham.itmeans.search.SearchActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.*;

public class SearchActivityTest {

    @Rule
    public ActivityTestRule<SearchActivity> testRule = new ActivityTestRule<>(SearchActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        launchActivity();
    }

    private void launchActivity() {
        testRule.launchActivity(null);
    }

    private void searchInit() {
//        ViewInteraction search = onView(allOf(
//                withId(R.id.action_search)
//        ));
//        search.perform(typeText("mask"));
        Intent intent = new Intent(SearchActivity.SEARCH_WORD);
        intent.putExtra(SearchActivity.STRING_WORD_EXTRA, "car");
        testRule.launchActivity(intent);
    }

    @Test
    public void atStart() {
        onView(withId(R.id.action_search)).check(matches(withText("")));
        onView(withId(R.id.entry)).check(matches(not(isDisplayed())));
        onView(withId(R.id.empty_view)).check(matches(isDisplayed()));
        onView(withId(R.id.progress)).check(matches(not(isDisplayed())));
    }

    @Test
    public void whenLoading() {
        searchInit();
        onView(withId(R.id.entry)).check(matches(not(isDisplayed())));
        onView(withId(R.id.empty_view)).check(matches(not(isDisplayed())));
        onView(withId(R.id.progress)).check(matches(isDisplayed()));
    }


    @Test
    public void afterSuccess() {
        searchInit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.entry)).check(matches(isDisplayed()));
        onView(withId(R.id.progress)).check(matches(not(isDisplayed())));
        onView(withId(R.id.empty_view)).check(matches(not(isDisplayed())));
    }


    @Test
    public void testSearchFromIntent() {
        searchInit();
        onView(allOf(
                withId(R.id.entry),
                isDisplayed()
        )).check(matches(withText("car")));
    }
}