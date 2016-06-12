package com.stustirling.moviedbshowcase.tests;

import android.content.Context;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.stustirling.moviedbshowcase.BaseTest;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;
import com.stustirling.moviedbshowcase.moviedetails.MovieDetailsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.Locale;

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
public class MovieDetailsActivityTest extends BaseTest {

    @Rule
    public ActivityTestRule<MovieDetailsActivity> activityRule = new ActivityTestRule<>(MovieDetailsActivity.class,
            true,
            false);

    private static final int MMFR_ID = 76341;
    private static final String MMFR_TITLE = "Mad Max: Fury Road";
    private static final String MMFR_OVERVIEW = "An apocalyptic story set in the furthest reaches of our planet, in a stark desert landscape where humanity is broken, and most everyone is crazed fighting for the necessities of life. Within this world exist two rebels on the run who just might be able to restore order. There's Max, a man of action and a man of few words, who seeks peace of mind following the loss of his wife and child in the aftermath of the chaos. And Furiosa, a woman of action and a woman who believes her path to survival may be achieved if she can make it across the desert back to her childhood homeland.";
    private static final float MMFR_VOTE_AVG = 7.3f;
    private static final Date MMFR_RELEASE_DATE = new Date();
    private static final String MMFR_POSTER_PATH = "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg";
    private MovieSummaryModel madMax;

    @Before
    public void setUp() {
        super.setUp();

        madMax = new MovieSummaryModel();
        madMax.setId(MMFR_ID);
        madMax.setTitle(MMFR_TITLE);
        madMax.setOverview(MMFR_OVERVIEW);
        madMax.setRating(MMFR_VOTE_AVG);
        madMax.setReleaseDate(MMFR_RELEASE_DATE);
        madMax.setPosterPath(null);
    }

    @Test
    public void shouldCreateCorrectIntent() {
        Context context = mock(Context.class);
        Intent intent = MovieDetailsActivity.launchIntent(context,madMax);
        MovieSummaryModel movieSummary = intent.getParcelableExtra(MovieDetailsActivity.MOVIE_SUMMARY_MODEL);
        assertEquals(madMax,movieSummary);
    }

    @Test
    public void shouldDisplayMovieData() {
        Context context = mock(Context.class);
        Intent intent = MovieDetailsActivity.launchIntent(context,madMax);
        activityRule.launchActivity(intent);

        onView(withId(R.id.sv_mda_container)).check(matches(isDisplayed()));

        onView(withText(MMFR_TITLE)).check(matches(isDisplayed()));
        onView(withText(MMFR_OVERVIEW)).check(matches(isDisplayed()));
        onView(withText(String.format(Locale.getDefault(),"%.1f",MMFR_VOTE_AVG)))
                .check(matches(isDisplayed()));
    }
}
