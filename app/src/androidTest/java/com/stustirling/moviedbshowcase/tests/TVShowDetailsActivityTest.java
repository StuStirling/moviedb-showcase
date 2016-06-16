package com.stustirling.moviedbshowcase.tests;

import android.content.Context;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.model.TVShowModel;
import com.stustirling.moviedbshowcase.details.tvshow.TVShowDetailsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by Stu Stirling on 12/06/16.
 */
@RunWith(AndroidJUnit4.class)
public class TVShowDetailsActivityTest {

    @Rule
    public ActivityTestRule<TVShowDetailsActivity> activityRule = new ActivityTestRule<>(TVShowDetailsActivity.class,
            true,
            false);

    private static final int ID = 76341;
    private static final String NAME = "Starge SG-1";
    private static final String OVERVIEW = "The story of Stargate SG-1 begins about a year after the events of the feature film, when the United States government learns that an ancient alien device called the Stargate can access a network of such devices on a multitude of planets. SG-1 is an elite Air Force special operations team, one of more than two dozen teams from Earth who explore the galaxy and defend against alien threats such as the Goa'uld, Replicators, and the Ori.";
    private static final float VOTE_AVG = 8.0f;
    private static final int VOTE_COUNT = 100;
    private static final Date FIRST_AIR_DATE = new Date();
    private static final String POSTER_PATH = "/mWnHXN6uWFAnySGxN9TljNKnwj6.jpg";

    private TVShowModel tvShow;

    @Before
    public void setUp() {
        tvShow = new TVShowModel();
        tvShow.setId(ID);
        tvShow.setName(NAME);
        tvShow.setOverview(OVERVIEW);
        tvShow.setVoteAvg(VOTE_AVG);
        tvShow.setVoteCount(VOTE_COUNT);
        tvShow.setFirstAirDate(FIRST_AIR_DATE);
    }

    @Test
    public void shouldCreateCorrectIntent() {
        Context context = mock(Context.class);
        Intent intent = TVShowDetailsActivity.launchIntent(context,tvShow);
        TVShowModel model = intent.getParcelableExtra(TVShowDetailsActivity.TV_SHOW_MODEL);
        assertEquals(tvShow,model);
    }

    @Test
    public void shouldDisplayTVShowData() {
        Context context = mock(Context.class);
        Intent intent = TVShowDetailsActivity.launchIntent(context,tvShow);
        activityRule.launchActivity(intent);

        onView(withId(R.id.ll_tsda_container)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_tsda_poster)).check(matches(isDisplayed()));

        onView(withText(OVERVIEW)).check(matches(isDisplayed()));

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        onView(withId(R.id.tv_tsda_first_aired_value))
                .check(matches(withText(
                        format.format(FIRST_AIR_DATE))));
        onView(withId(R.id.tv_tsda_rating)).check(matches(withText("8.0/10")));
        onView(withId(R.id.tv_tsda_vote_count)).check(matches(withText("(100 votes)")));
    }
}
