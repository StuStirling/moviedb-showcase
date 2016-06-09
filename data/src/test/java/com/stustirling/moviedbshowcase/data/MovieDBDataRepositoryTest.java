package com.stustirling.moviedbshowcase.data;

import com.stustirling.moviedbshowcase.data.entity.MovieSummaryEntity;
import com.stustirling.moviedbshowcase.data.entity.mapper.EntityDataMapper;
import com.stustirling.moviedbshowcase.data.rest.MovieDBService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    }


    @Test
    public void testGetPopularMoviesHappyCase() {
        movieDataRepo = new MovieDBDataRepository(mockService, new EntityDataMapper() );
        MovieSummaryEntity movieSummary = new MovieSummaryEntity();
        List<MovieSummaryEntity> movieSummaries = new ArrayList<>();
        movieSummaries.add(movieSummary);
        given(mockService.getPopularMovies()).willReturn(Observable.just(movieSummaries));

        movieDataRepo.getPopularMovies(20);

        verify(mockService,times(1)).getPopularMovies();
    }

}
