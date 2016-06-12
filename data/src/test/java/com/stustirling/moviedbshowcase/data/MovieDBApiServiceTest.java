package com.stustirling.moviedbshowcase.data;

import com.stustirling.moviedbshowcase.data.entity.MovieDetailsEntity;
import com.stustirling.moviedbshowcase.data.entity.MovieSummaryEntity;
import com.stustirling.moviedbshowcase.data.entity.PopularMoviesResponse;
import com.stustirling.moviedbshowcase.data.rest.MovieDBApi;
import com.stustirling.moviedbshowcase.data.rest.MovieDBApiService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public class MovieDBApiServiceTest {

    @Mock MovieDBApi mockApi;

    MovieDBApiService apiService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        apiService = new MovieDBApiService(mockApi);
    }

    @Test
    public void testPopularMoviesRetrievalConcatenation() {
        MovieSummaryEntity zootopia = new MovieSummaryEntity();

        PopularMoviesResponse responseOne = new PopularMoviesResponse();
        responseOne.results = new ArrayList<>();
        responseOne.results.add(zootopia);
        when(mockApi.getPopularMovies(1)).thenReturn(Observable.just(responseOne));

        TestSubscriber<List<MovieSummaryEntity>> testSubscriber = new TestSubscriber<>();
        apiService.getPopularMovies().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        List<List<MovieSummaryEntity>> entityLists = testSubscriber.getOnNextEvents();
        assertEquals(1,entityLists.size());
        List<MovieSummaryEntity> zootopiaContainer = entityLists.get(0);
        assertEquals(zootopia,zootopiaContainer.get(0));

        verify(mockApi,times(1)).getPopularMovies(anyInt());
    }

    @Test
    public void shouldReturnMovieDetails() {
        MovieDBApiService apiService = new MovieDBApiService(mockApi);

        MovieDetailsEntity waterworld = new MovieDetailsEntity();
        when(mockApi.getMovieDetails(9804)).thenReturn(Observable.just(waterworld));

        TestSubscriber<MovieDetailsEntity> testSubscriber = new TestSubscriber<>();
        apiService.getMovieDetails(9804).subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        List<MovieDetailsEntity> movieDetails = testSubscriber.getOnNextEvents();
        assertEquals(1,movieDetails.size());
        assertEquals(waterworld,movieDetails.get(0));
        verify(mockApi,times(1)).getMovieDetails(anyInt());
    }

}
