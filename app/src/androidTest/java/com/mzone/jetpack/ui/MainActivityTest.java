package com.mzone.jetpack.ui;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.mzone.jetpack.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void toDetailMovieActivityTest() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()));
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.detail_release)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_release)).check(matches(withText("2019-10-18")));
    }

    @Test
    public void toDetailTvActivityTest() {
        onView(withId(R.id.viewpager)).perform(swipeLeft());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.rvTV)).check(matches(isDisplayed()));
        onView(withId(R.id.rvTV)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.detail_release)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_release)).check(matches(withText("2005-09-13")));
    }
}