package com.mzone.jetpack.ui.tv.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.mzone.jetpack.R;
import com.mzone.jetpack.data.source.local.entity.TvEntity;
import com.mzone.jetpack.utils.FakeRemoteDataDummy;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailTvActivityTest {

    private TvEntity tvEntity = FakeRemoteDataDummy.generateRemoteDummyTv().get(1);

    @Rule
    public ActivityTestRule<DetailTvActivity> activityRule = new ActivityTestRule<DetailTvActivity>(DetailTvActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, DetailTvActivity.class);
            intent.putExtra(DetailTvActivity.EXTRA_ID_TV, tvEntity.getIdTv());
            return intent;
        }
    };

    @Test
    public void loadTvById() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.detail_original)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_original)).check(matches(withText(tvEntity.getOriginalTitleTv())));
        onView(withId(R.id.detail_overview)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_overview)).check(matches(withText(tvEntity.getOverviewTv())));
        onView(withId(R.id.detail_release)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_release)).check(matches(withText(tvEntity.getFirstAirDateTv())));
        onView(withId(R.id.detail_image)).check(matches(isDisplayed()));
        onView(withId(R.id.collapsing)).check(matches(isDisplayed()));
    }
}