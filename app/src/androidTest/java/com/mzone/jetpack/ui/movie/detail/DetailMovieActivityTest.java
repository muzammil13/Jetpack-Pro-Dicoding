package com.mzone.jetpack.ui.movie.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.mzone.jetpack.R;
import com.mzone.jetpack.data.source.local.entity.MovieEntity;
import com.mzone.jetpack.utils.FakeRemoteDataDummy;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailMovieActivityTest {

    private MovieEntity movieEntity = FakeRemoteDataDummy.generateRemoteDummyMovies().get(1);

    @Rule
    public ActivityTestRule<DetailMovieActivity> activityRule = new ActivityTestRule<DetailMovieActivity>(DetailMovieActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_ID_MOVIE, movieEntity.getIdMovie());
            return intent;
        }
    };

    @Test
    public void loadMovieById() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.detail_original)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_original)).check(matches(withText(movieEntity.getOriginalTitleMovie())));
        onView(withId(R.id.detail_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_overview)).check(matches(withText(movieEntity.getOverviewMovie())));
        onView(withId(R.id.detail_release)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_release)).check(matches(withText(movieEntity.getReleaseDateMovie())));
        onView(withId(R.id.detail_image)).check(matches(isDisplayed()));
        onView(withId(R.id.collapsing)).check(matches(isDisplayed()));
    }
}