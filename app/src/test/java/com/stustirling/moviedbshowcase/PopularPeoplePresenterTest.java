package com.stustirling.moviedbshowcase;

import com.stustirling.moviedbshowcase.domain.interactor.GetPopularPeople;
import com.stustirling.moviedbshowcase.model.mapper.MovieSummaryModelMapper;
import com.stustirling.moviedbshowcase.model.mapper.PersonModelMapper;
import com.stustirling.moviedbshowcase.popular.PopularPresenter;
import com.stustirling.moviedbshowcase.popular.popularpeople.PopularPeoplePresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Stu Stirling on 15/06/16.
 */
public class PopularPeoplePresenterTest {

    @Mock PopularPresenter.PopularView mockedView;
    @Mock GetPopularPeople  mockedUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInitialisation() {
        PopularPeoplePresenter presenter = new PopularPeoplePresenter(mockedUseCase, new PersonModelMapper( new MovieSummaryModelMapper()));
        presenter.init(mockedView);

        verify(mockedView,times(1)).loading(true);
        verify(mockedUseCase,times(1)).execute(any(Subscriber.class));
    }



}
