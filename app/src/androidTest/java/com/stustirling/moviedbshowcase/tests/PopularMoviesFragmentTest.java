package com.stustirling.moviedbshowcase.tests;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.stustirling.moviedbshowcase.BaseTest;
import com.stustirling.moviedbshowcase.MainActivity;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.model.MovieSummaryModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

/**
 * Created by Stu Stirling on 10/06/16.
 */
@RunWith(AndroidJUnit4.class)
public class PopularMoviesFragmentTest extends BaseTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class,
            true,
            false);

    private static final String INTERSTELLAR_TITLE = "Interstellar";
    private static final String INTERSTELLAR_OVERVIEW = "Interstellar chronicles the adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage.";
    private static final float INTERSTELLAR_RATING = 8.2f;
    private static final String INTERSTELLAR_POSTER = "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg";

    @Before
    public void setUp() {
        super.setUp();

        List<MovieSummary> entities = new ArrayList<>();
        MovieSummary interstellar = new MovieSummary();
        interstellar.setId(231);
        interstellar.setTitle(INTERSTELLAR_TITLE);
        interstellar.setOverview(INTERSTELLAR_OVERVIEW);
        interstellar.setRating(INTERSTELLAR_RATING);
        interstellar.setPosterPath(null);
        Calendar releaseDate = Calendar.getInstance();
        releaseDate.set(2015,11,17);
        interstellar.setReleaseDate(releaseDate.getTime());
        entities.add(interstellar);

        when(mockRepo.getPopularMovies()).thenReturn(Observable.just(entities));
    }

    @Test
    public void shouldShowPopularMoviesFragment() {
        activityRule.launchActivity( null );

        onView(ViewMatchers.withId(R.id.rv_pmf_movies)).check(matches(isDisplayed()));

        onView(withText(INTERSTELLAR_TITLE)).check(matches(isDisplayed()));
        onView(withText(INTERSTELLAR_OVERVIEW)).check(matches(isDisplayed()));
        onView(withText(Float.valueOf(INTERSTELLAR_RATING).toString())).check(matches(isDisplayed()));
        onView(withText("2015")).check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowMovieDetails() {
        activityRule.launchActivity( null );

        onView(withId(R.id.rv_pmf_movies))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        onView(withId(R.id.sv_mda_container)).check(matches(isDisplayed()));

        onView(withText(INTERSTELLAR_TITLE)).check(matches(isDisplayed()));
        onView(withText(INTERSTELLAR_OVERVIEW)).check(matches(isDisplayed()));
    }

}
