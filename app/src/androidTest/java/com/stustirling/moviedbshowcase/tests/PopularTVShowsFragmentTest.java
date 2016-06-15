package com.stustirling.moviedbshowcase.tests;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.stustirling.moviedbshowcase.BaseTest;
import com.stustirling.moviedbshowcase.MainActivity;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.TVShow;

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
public class PopularTVShowsFragmentTest extends BaseTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class,
            true,
            false);

    private static final String BREAKING_BAD_NAME = "Breaking Bad";
    private static final String BREAKING_BAD_OVERVIEW = "Breaking Bad is an American crime drama television series created and produced by Vince Gilligan. Set and produced in Albuquerque, New Mexico, Breaking Bad is the story of Walter White, a struggling high school chemistry teacher who is diagnosed with inoperable lung cancer at the beginning of the series. He turns to a life of crime, producing and selling methamphetamine, in order to secure his family's financial future before he dies, teaming with his former student, Jesse Pinkman. Heavily serialized, the series is known for positioning its characters in seemingly inextricable corners and has been labeled a contemporary western by its creator.";
    private static final float BREAKING_BAD_RATING = 8.3f;

    @Before
    public void setUp() {
        super.setUp();

        List<TVShow> entities = new ArrayList<>();
        TVShow breakingBad = new TVShow();
        breakingBad.setId(231);
        breakingBad.setName(BREAKING_BAD_NAME);
        breakingBad.setOverview(BREAKING_BAD_OVERVIEW);
        breakingBad.setVoteAvg(BREAKING_BAD_RATING);
        breakingBad.setPosterPath(null);
        Calendar releaseDate = Calendar.getInstance();
        releaseDate.set(2008,11,17);
        breakingBad.setFirstAirDate(releaseDate.getTime());
        entities.add(breakingBad);

        when(mockRepo.getPopularMovies()).thenReturn(Observable.<List<MovieSummary>>empty());

        when(mockRepo.getPopularTVShows()).thenReturn(Observable.just(entities));

//        when(mockRepo.getMovieDetails(231)).thenReturn(Observable.<MovieDetails>empty());
    }

    @Test
    public void shouldShowPopularTVShowsFragment() {
        activityRule.launchActivity( null );

        onView(withText(R.string.tab2_title)).perform(click());
        onView(withId(R.id.rv_ptf_tvshows)).check(matches(isDisplayed()));

        onView(withText(BREAKING_BAD_NAME)).check(matches(isDisplayed()));
        onView(withText(BREAKING_BAD_OVERVIEW)).check(matches(isDisplayed()));
        onView(withText(Float.valueOf(BREAKING_BAD_RATING).toString())).check(matches(isDisplayed()));
        onView(withText("2008")).check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowTVShowDetails() {
        activityRule.launchActivity( null );

        onView(withText(R.string.tab2_title)).perform(click());

        onView(withId(R.id.rv_ptf_tvshows))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        onView(withId(R.id.ll_tsda_container)).check(matches(isDisplayed()));

        onView(withText(BREAKING_BAD_OVERVIEW)).check(matches(isDisplayed()));
    }

}
