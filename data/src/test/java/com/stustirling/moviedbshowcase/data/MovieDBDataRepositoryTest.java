package com.stustirling.moviedbshowcase.data;

import com.stustirling.moviedbshowcase.data.entity.mapper.EntityDataMapper;
import com.stustirling.moviedbshowcase.data.entity.movies.MovieDetailsEntity;
import com.stustirling.moviedbshowcase.data.entity.movies.MovieSummaryEntity;
import com.stustirling.moviedbshowcase.data.entity.person.PersonEntity;
import com.stustirling.moviedbshowcase.data.entity.tvshows.TVShowEntity;
import com.stustirling.moviedbshowcase.data.rest.MovieDBService;
import com.stustirling.moviedbshowcase.domain.MovieDetails;
import com.stustirling.moviedbshowcase.domain.MovieSummary;
import com.stustirling.moviedbshowcase.domain.Person;
import com.stustirling.moviedbshowcase.domain.TVShow;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public class MovieDBDataRepositoryTest {

    @Mock
    MovieDBService mockService;
    private MovieDBDataRepository movieDataRepo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        movieDataRepo = new MovieDBDataRepository(mockService, new EntityDataMapper() );
    }


    @Test
    public void shouldReturnSuppliedPopularMovies() {
        MovieSummaryEntity movieSummary = new MovieSummaryEntity();
        List<MovieSummaryEntity> movieSummaries = new ArrayList<>();
        movieSummaries.add(movieSummary);
        given(mockService.getPopularMovies()).willReturn(Observable.just(movieSummaries));

        TestSubscriber<List<MovieSummary>> testSubscriber = new TestSubscriber<>();
        movieDataRepo.getPopularMovies().subscribe(testSubscriber);

        verify(mockService,times(1)).getPopularMovies();
        testSubscriber.assertNoErrors();
        List<List<MovieSummary>> summaryOutputs = testSubscriber.getOnNextEvents();
        assertEquals(1,summaryOutputs.size());
    }

    @Test
    public void shouldReturnSuppliedMovie() {
        MovieDetailsEntity spaceJam = new MovieDetailsEntity();
        when(mockService.getMovieDetails(231)).thenReturn(Observable.just(spaceJam));

        TestSubscriber<MovieDetails> testSubscriber = new TestSubscriber<>();
        movieDataRepo.getMovieDetails(231).subscribe(testSubscriber);

        verify(mockService,times(1)).getMovieDetails(231);
        testSubscriber.assertNoErrors();
        List<MovieDetails> outputs = testSubscriber.getOnNextEvents();
        assertEquals(1,outputs.size());
    }

    @Test
    public void shouldReturnSuppliedPopularTVShows() {
        TVShowEntity tvShowEntity = new TVShowEntity();
        List<TVShowEntity> showEntities = new ArrayList<>();
        showEntities.add(tvShowEntity);
        given(mockService.getPopularTVShows()).willReturn(Observable.just(showEntities));

        TestSubscriber<List<TVShow>> testSubscriber = new TestSubscriber<>();
        movieDataRepo.getPopularTVShows().subscribe(testSubscriber);

        verify(mockService,times(1)).getPopularTVShows();
        testSubscriber.assertNoErrors();
        List<List<TVShow>> outputs = testSubscriber.getOnNextEvents();
        assertEquals(1,outputs.size());
    }

    @Test
    public void shouldReturnSuppliedPopularPeople() {
        PersonEntity personEntity = new PersonEntity();
        List<PersonEntity> peopleEntities = new ArrayList<>();
        peopleEntities.add(personEntity);
        given(mockService.getPopularPeople()).willReturn(Observable.just(peopleEntities));

        TestSubscriber<List<Person>> testSubscriber = new TestSubscriber<>();
        movieDataRepo.getPopularPeople().subscribe(testSubscriber);

        verify(mockService,times(1)).getPopularPeople();
        testSubscriber.assertNoErrors();
        List<List<Person>> outputs = testSubscriber.getOnNextEvents();
        assertEquals(1,outputs.size());
    }

}
