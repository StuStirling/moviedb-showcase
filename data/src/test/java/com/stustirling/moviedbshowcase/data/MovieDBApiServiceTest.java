package com.stustirling.moviedbshowcase.data;

import com.stustirling.moviedbshowcase.data.entity.movies.MovieDetailsEntity;
import com.stustirling.moviedbshowcase.data.entity.movies.MovieSummaryEntity;
import com.stustirling.moviedbshowcase.data.entity.movies.PopularMoviesResponse;
import com.stustirling.moviedbshowcase.data.entity.person.PersonEntity;
import com.stustirling.moviedbshowcase.data.entity.person.PopularPeopleResponse;
import com.stustirling.moviedbshowcase.data.entity.tvshows.PopularTVShowsResponse;
import com.stustirling.moviedbshowcase.data.entity.tvshows.TVShowEntity;
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
import static org.mockito.Mockito.mock;
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

    @Test
    public void shouldReturnTVShows() {
        TVShowEntity tvshow = new TVShowEntity();

        PopularTVShowsResponse responseOne = new PopularTVShowsResponse();
        responseOne.results = new ArrayList<>();
        responseOne.results.add(tvshow);
        when(mockApi.getPopularTVShows()).thenReturn(Observable.just(responseOne));

        TestSubscriber<List<TVShowEntity>> testSubscriber = new TestSubscriber<>();
        apiService.getPopularTVShows().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        List<List<TVShowEntity>> entityLists = testSubscriber.getOnNextEvents();
        assertEquals(1,entityLists.size());
        List<TVShowEntity> tvshowContainer = entityLists.get(0);
        assertEquals(1,tvshowContainer.size());
        assertEquals(tvshow,tvshowContainer.get(0));

        verify(mockApi,times(1)).getPopularTVShows();
    }

    @Test
    public void shouldReturnPopularPeople() {
        PersonEntity person = new PersonEntity();
        PopularPeopleResponse response = mock(PopularPeopleResponse.class);
        List<PersonEntity> results = new ArrayList<>();
        results.add(person);
        when(response.getPeople()).thenReturn(results);
        when(mockApi.getPopularPeople()).thenReturn(Observable.just(response));

        TestSubscriber<List<PersonEntity>> testSubscriber = new TestSubscriber<>();
        apiService.getPopularPeople().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        List<List<PersonEntity>> entityLists = testSubscriber.getOnNextEvents();
        assertEquals(1,entityLists.size());
        List<PersonEntity> personContainer = entityLists.get(0);
        assertEquals(1,personContainer.size());
        assertEquals(person,personContainer.get(0));

        verify(mockApi,times(1)).getPopularPeople();
    }

}
