package com.stustirling.moviedbshowcase.domain;

import com.stustirling.moviedbshowcase.domain.interactor.GetMovieDetails;
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
public class GetMovieDetailsTest {

    private GetMovieDetails getMovieDetails;
    @Mock private MovieDBRepository mockRepo;
    @Mock private Scheduler mockExector;
    @Mock private Scheduler mockPostExecution;
    private static final int movieId = 9423;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getMovieDetails = new GetMovieDetails(mockRepo,mockExector,mockPostExecution,movieId);
    }

    @Test
    public void testGetMovieDetailsHappyCase() {
        getMovieDetails.buildUseCaseObservable();

        verify(mockRepo,times(1)).getMovieDetails(movieId);
        verifyNoMoreInteractions(mockRepo);
    }

}
