package com.stustirling.moviedbshowcase;

import com.stustirling.moviedbshowcase.domain.interactor.GetTop20PopularTVShows;
import com.stustirling.moviedbshowcase.model.mapper.TVShowModelMapper;
import com.stustirling.moviedbshowcase.popular.PopularPresenter;
import com.stustirling.moviedbshowcase.popular.tvshows.PopularTVShowsPresenter;

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
 * Created by Stu Stirling on 14/06/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PopularTVShowsPresenterTest {

    @Mock
    PopularPresenter.PopularView mockedView;
    @Mock GetTop20PopularTVShows mockedUseCase;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInitialisation() {
        PopularTVShowsPresenter presenter = new PopularTVShowsPresenter(mockedUseCase, new TVShowModelMapper());
        presenter.init(mockedView);

        verify(mockedView,times(1)).loading(true);
        verify(mockedUseCase,times(1)).execute(any(Subscriber.class));
    }

}
