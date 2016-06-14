package com.stustirling.moviedbshowcase.tests;

import android.content.Context;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.stustirling.moviedbshowcase.BaseTest;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.domain.MovieDetails;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;
import com.stustirling.moviedbshowcase.moviedetails.MovieDetailsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    private static final int MMFR_REVENUE = 378436354;
    private static final String MMFR_IMDB_ID = "tt1392190";
    private static final int MMFR_BUDGET = 150000000;
    private static final String MMFR_HOMEPAGE = "http://www.madmaxmovie.com/";
    private static final int MMFR_RUNTIME = 120;
    private static final int MMFR_VOTE_COUNT = 4705;


    private MovieSummaryModel madMax;

    private MovieDetails madMaxDetails;

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

        madMaxDetails = new MovieDetails();
        madMaxDetails.setId(MMFR_ID);
        madMaxDetails.setTitle(MMFR_TITLE);
        madMaxDetails.setRevenue(MMFR_REVENUE);
        madMaxDetails.setBudget(MMFR_BUDGET);
        madMaxDetails.setHomepage(MMFR_HOMEPAGE);
        madMaxDetails.setImdbId(MMFR_IMDB_ID);
        madMaxDetails.setRuntime(MMFR_RUNTIME);
        madMaxDetails.setVoteCount(MMFR_VOTE_COUNT);

        when(mockRepo.getMovieDetails(MMFR_ID)).thenReturn(Observable.just(madMaxDetails));
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

        onView(withId(R.id.ll_mda_container)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_mda_poster)).check(matches(isDisplayed()));

        onView(withText(MMFR_OVERVIEW)).check(matches(isDisplayed()));

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        onView(withId(R.id.tv_mda_released_value))
                .check(matches(withText(
                        format.format(MMFR_RELEASE_DATE))));
        onView(withId(R.id.tv_mda_rating)).check(matches(withText("7.3/10")));
        onView(withId(R.id.tv_mda_budget_value)).check(matches(withText("$150,000,000")));
        onView(withId(R.id.tv_mda_revenue_value)).check(matches(withText("$378,436,354")));
        onView(withId(R.id.tv_mda_runtime_value)).check(matches(withText("120 minutes")));
        onView(withId(R.id.tv_mda_vote_count)).check(matches(withText("(4705 votes)")));
    }
}
