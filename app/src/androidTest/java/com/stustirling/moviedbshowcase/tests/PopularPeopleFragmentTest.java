package com.stustirling.moviedbshowcase.tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.stustirling.moviedbshowcase.BaseTest;
import com.stustirling.moviedbshowcase.MainActivity;
import com.stustirling.moviedbshowcase.R;
import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.Person;
import com.stustirling.moviedbshowcase.domain.TVShow;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

/**
 * Created by Stu Stirling on 10/06/16.
 */
@RunWith(AndroidJUnit4.class)
public class PopularPeopleFragmentTest extends BaseTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class,
            true,
            false);

    private static final String NAME = "Scarlett Johansson";
    private static final String KNOWN_FOR_1_TITLE = "The Avengers";
    private static final String KNOWN_FOR_2_TITLE = "Iron Man 2";

    @Before
    public void setUp() {
        super.setUp();

        when(mockConnTester.isThereAnInternetConnection()).thenReturn(true);

        List<Person> entities = new ArrayList<>();
        Person person = new Person();
        person.setId(231);
        person.setName(NAME);
        person.setProfilePath(null);

        MovieSummary avengers = new MovieSummary();
        avengers.setTitle(KNOWN_FOR_1_TITLE);
        MovieSummary ironMan = new MovieSummary();
        ironMan.setTitle(KNOWN_FOR_2_TITLE);
        List<MovieSummary> knownFor = new ArrayList<>();
        knownFor.add(avengers);
        knownFor.add(ironMan);
        person.setKnownFor(knownFor);
        entities.add(person);

        when(mockRepo.getPopularMovies()).thenReturn(Observable.<List<MovieSummary>>empty());

        when(mockRepo.getPopularTVShows()).thenReturn(Observable.<List<TVShow>>empty());

        when(mockRepo.getPopularPeople()).thenReturn(Observable.just(entities));
    }

    @Test
    public void shouldShowPopularTVShowsFragment() {
        activityRule.launchActivity( null );

        onView(withText(R.string.tab3_title)).perform(click());

        onView(withText(NAME)).check(matches(isDisplayed()));
        onView(withText(KNOWN_FOR_1_TITLE+", "+KNOWN_FOR_2_TITLE)).check(matches(isDisplayed()));

    }
}
