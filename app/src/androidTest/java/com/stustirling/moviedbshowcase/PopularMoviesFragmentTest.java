package com.stustirling.moviedbshowcase;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Stu Stirling on 10/06/16.
 */
@RunWith(AndroidJUnit4.class)
public class PopularMoviesFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

//    @Mock GetTop20PopularMovies mockUseCase;

    @Before
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldShowPopularMoviesFragment() {
        onView(withId(R.id.rv_pmf_movies)).check(matches(isDisplayed()));

        onView(withText("Star Wars: The Force Awakens")).check(matches(isDisplayed()));
    }

}
