package com.stustirling.moviedbshowcase;

import com.stustirling.moviedbshowcase.domain.interactor.GetTop20PopularMovies;
import com.stustirling.moviedbshowcase.popularmovies.PopularMoviesController;
import com.stustirling.moviedbshowcase.popularmovies.PopularMoviesView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Stu Stirling on 06/06/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PopularMoviesControllerTest {

    @Mock
    PopularMoviesView mockedView;
    @Mock GetTop20PopularMovies mockedUseCase;
    @Mock GetTop20PopularMovies getTop20PopularMovies;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInitialisation() {
        PopularMoviesController controller = new PopularMoviesController(mockedView,mockedUseCase);
        controller.init();

        verify(mockedView,times(1)).loading(true);
        verify(mockedUseCase,times(1)).execute(any(Subscriber.class));
    }

}
