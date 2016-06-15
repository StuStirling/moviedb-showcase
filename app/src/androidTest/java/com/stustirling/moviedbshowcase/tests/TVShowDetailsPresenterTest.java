package com.stustirling.moviedbshowcase.tests;

import com.stustirling.moviedbshowcase.model.TVShowModel;
import com.stustirling.moviedbshowcase.tvshowdetails.TVShowDetailsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Stu Stirling on 12/06/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class TVShowDetailsPresenterTest {

    @Mock TVShowDetailsPresenter.TVShowDetailsView mockedView;
    @Mock TVShowModel mockTVShow;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInitialisation() {
        TVShowDetailsPresenter presenter = new TVShowDetailsPresenter(mockTVShow,mockedView);
        presenter.init();

        verify(mockedView,times(1)).displayTVShow(mockTVShow);
    }

}
