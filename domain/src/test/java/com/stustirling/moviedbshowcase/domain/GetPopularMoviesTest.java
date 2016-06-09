package com.stustirling.moviedbshowcase.domain;

import com.stustirling.moviedbshowcase.domain.interactor.GetTop20PopularMovies;
import com.stustirling.moviedbshowcase.domain.repository.MovieDBRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Scheduler;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by Stu Stirling on 07/06/16.
 */
public class GetPopularMoviesTest {

    private GetTop20PopularMovies getTop20PopularMovies;
    @Mock private MovieDBRepository mockRepo;
    @Mock private Scheduler mockExector;
    @Mock private Scheduler mockPostExecution;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getTop20PopularMovies = new GetTop20PopularMovies(mockRepo,mockExector,mockPostExecution);
    }

    @Test
    public void testGetPopularMoviesUseCaseHappyCase() {
        getTop20PopularMovies.buildUseCaseObservable();

        verify(mockRepo,times(1)).getPopularMovies(20);
        verifyNoMoreInteractions(mockRepo);
    }

}
