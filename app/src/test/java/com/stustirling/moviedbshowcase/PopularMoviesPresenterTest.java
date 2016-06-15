package com.stustirling.moviedbshowcase;

import com.stustirling.moviedbshowcase.domain.interactor.GetTop20PopularMovies;
import com.stustirling.moviedbshowcase.model.mapper.MovieSummaryModelMapper;
import com.stustirling.moviedbshowcase.popular.PopularPresenter;
import com.stustirling.moviedbshowcase.popular.popularmovies.PopularMoviesPresenter;

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
public class PopularMoviesPresenterTest {

    @Mock
    PopularPresenter.PopularView mockedView;
    @Mock GetTop20PopularMovies mockedUseCase;

    private PopularMoviesPresenter presenter;

   /* @Captor
    ArgumentCaptor<ArrayList<MovieSummaryModel>> filterCaptor;*/


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new PopularMoviesPresenter(mockedUseCase,new MovieSummaryModelMapper());
    }

    @Test
    public void testInitialisation() {
        presenter.init(mockedView);
        verify(mockedView,times(1)).loading(true);
        verify(mockedUseCase,times(1)).execute(any(Subscriber.class));
    }


    /* TODO: Get this test working correctly. Breaks when AndroidTest and MovieSummaryModel is Parcelable */
    /*@Test
    public void testFiltering() {
        MovieSummary ironMan = new MovieSummary();
        ironMan.setTitle("Iron Man");

        MovieSummary captainAmerica = new MovieSummary();
        captainAmerica.setTitle("Captain America");

        MovieSummary blackPanther = new MovieSummary();
        blackPanther.setTitle("Black Panther");

        List<MovieSummary> movieSummaries = new ArrayList<>();
        movieSummaries.add(ironMan);
        movieSummaries.add(captainAmerica);
        movieSummaries.add(blackPanther);

        when(mockedUseCase.buildUseCaseObservable())
                .thenReturn(Observable.just(movieSummaries));

        presenter.init(mockedView);

        presenter.filterItems("man");
        verify(mockedView).showFilteredMovies(filterCaptor.capture());

        final List<MovieSummaryModel> manFilter = filterCaptor.getValue();
        assertEquals(1,manFilter.size());
        assertEquals(ironMan.getTitle(),manFilter.get(0).getTitle());

        presenter.filterItems("CaP");
        verify(mockedView).showFilteredMovies(filterCaptor.capture());

        final List<MovieSummaryModel> capFilter = filterCaptor.getValue();
        assertEquals(1,capFilter.size());
        assertEquals(captainAmerica.getTitle(),capFilter.get(0).getTitle());


        presenter.filterItems("ck PA");
        verify(mockedView).showFilteredMovies(filterCaptor.capture());

        final List<MovieSummaryModel> ckpaFilter = filterCaptor.getValue();
        assertEquals(1,ckpaFilter.size());
        assertEquals(blackPanther.getTitle(),ckpaFilter.get(0).getTitle());

    }*/

}
