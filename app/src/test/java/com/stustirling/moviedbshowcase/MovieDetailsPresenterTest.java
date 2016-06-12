package com.stustirling.moviedbshowcase;

import com.stustirling.moviedbshowcase.domain.interactor.GetMovieDetails;
import com.stustirling.moviedbshowcase.moviedetails.MovieDetailsPresenter;

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
 * Created by Stu Stirling on 12/06/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieDetailsPresenterTest {

    @Mock
    MovieDetailsPresenter.MovieDetailsView mockedView;
    @Mock GetMovieDetails mockedUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInitialisation() {
        MovieDetailsPresenter presenter = new MovieDetailsPresenter(mockedUseCase);
        presenter.init(mockedView);

        verify(mockedView,times(1)).loading(true);
        verify(mockedUseCase,times(1)).execute(any(Subscriber.class));
    }

}
