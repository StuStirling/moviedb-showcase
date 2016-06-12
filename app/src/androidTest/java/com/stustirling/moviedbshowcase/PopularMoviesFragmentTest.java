package com.stustirling.moviedbshowcase;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.stustirling.moviedbshowcase.domain.MovieSummary;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
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

    @Before
    public void setUp() {
        super.setUp();

        List<MovieSummary> entities = new ArrayList<>();
        MovieSummary zootopia = new MovieSummary();
        zootopia.setId(231);
        zootopia.setTitle("Interstellar");
        entities.add(zootopia);

        when(mockRepo.getPopularMovies()).thenReturn(Observable.just(entities));
    }

    @Test
    public void shouldShowPopularMoviesFragment() {
        activityRule.launchActivity( null );

        onView(withId(R.id.rv_pmf_movies)).check(matches(isDisplayed()));

        onView(withText("Interstellar")).check(matches(isDisplayed()));
//        onView(withText("Star Wars: The Force Awakens")).check(matches(isDisplayed()));
    }

}
